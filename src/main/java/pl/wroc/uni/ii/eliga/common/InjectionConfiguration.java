package pl.wroc.uni.ii.eliga.common;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;
import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;
import static pl.wroc.uni.ii.eliga.common.EligaInjector.getInstance;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.postgresql.Driver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import pl.wroc.uni.ii.eliga.db.DatabaseService;
import pl.wroc.uni.ii.eliga.db.hibernate.HibernateDatabaseService;
import pl.wroc.uni.ii.eliga.db.model.Course;
import pl.wroc.uni.ii.eliga.db.model.Mark;
import pl.wroc.uni.ii.eliga.db.model.Notice;
import pl.wroc.uni.ii.eliga.db.model.Parent;
import pl.wroc.uni.ii.eliga.db.model.Student;
import pl.wroc.uni.ii.eliga.db.model.Teacher;
import pl.wroc.uni.ii.eliga.mail.GmailSender;
import pl.wroc.uni.ii.eliga.mail.MailSender;

public class InjectionConfiguration extends AbstractModule {
  private static ThreadLocal<Session> threadLocalSession = new ThreadLocal<Session>();

  @Override
  protected void configure() {
    bindInterceptor(any(), annotatedWith(Transactional.class), new TransactionalInterceptor());
    bind(DatabaseService.class).to(HibernateDatabaseService.class);
    bind(MailSender.class).to(GmailSender.class);
  }

  @Provides
  public Session createHibernateSession() {
    return threadLocalSession.get();
  }

  @Provides
  @Singleton
  public SessionFactory createHibernateSessionFactory() {
    return getInstance(Configuration.class).buildSessionFactory();
  }

  @Provides
  @Singleton
  public Configuration createHibernateConfiguration() {
    Configuration configuration = new Configuration();
    addModelClasses(configuration);
    setDatabaseProperties(configuration);
    return configuration;
  }

  private void setDatabaseProperties(Configuration configuration) {
    configuration
        .setProperty(USER, "postgres")
        .setProperty(PASS, "postgres")
        .setProperty(URL, "jdbc:postgresql://localhost:5433/postgres")
        .setProperty(DRIVER, Driver.class.getCanonicalName())
        .setProperty(DIALECT, PostgreSQL82Dialect.class.getCanonicalName());
  }

  private void addModelClasses(Configuration configuration) {
    configuration
        .addAnnotatedClass(Course.class)
        .addAnnotatedClass(Mark.class)
        .addAnnotatedClass(Notice.class)
        .addAnnotatedClass(Student.class)
        .addAnnotatedClass(Teacher.class)
        .addAnnotatedClass(Parent.class);
  }

  private static class TransactionalInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
      if (threadLocalSession.get() != null
          && threadLocalSession.get().isOpen()) {
        Session session = threadLocalSession.get();
        Transaction transaction = session.getTransaction();

        try {
          Object result = invocation.proceed();
          //transaction.commit();
          return result;
        } catch (Throwable t) {
          //transaction.rollback();
          throw t;
        }
      }
      else {
        Session session = getInstance(SessionFactory.class).openSession();
        threadLocalSession.set(session);
        Transaction transaction = session.beginTransaction();

        try {
          Object result = invocation.proceed();
          transaction.commit();
          return result;
        } catch (Throwable t) {
          transaction.rollback();
          throw t;
        } finally {
          session.close();
        }
      }

    }
  }
}
