package pl.wroc.uni.ii.eliga.common;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.postgresql.Driver;
import pl.wroc.uni.ii.eliga.db.DatabaseService;
import pl.wroc.uni.ii.eliga.db.hibernate.HibernateDatabaseService;
import pl.wroc.uni.ii.eliga.db.model.*;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;
import static org.hibernate.cfg.AvailableSettings.*;
import static pl.wroc.uni.ii.eliga.common.EligaInjector.getInstance;

public class InjectionConfiguration extends AbstractModule {
  private static ThreadLocal<Session> threadLocalSession = new ThreadLocal<Session>();

  @Override
  protected void configure() {
    bindInterceptor(any(), annotatedWith(Transactional.class), new TransactionalInterceptor());
    bind(DatabaseService.class).to(HibernateDatabaseService.class);
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
