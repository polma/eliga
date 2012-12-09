package pl.wroc.uni.ii.eliga.db.hibernate;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pl.wroc.uni.ii.eliga.db.DatabaseService;
import pl.wroc.uni.ii.eliga.db.model.Mark;
import pl.wroc.uni.ii.eliga.db.model.Notice;

import java.util.List;

@Singleton
public class HibernateDatabaseService implements DatabaseService {
  @Inject
  Injector injector;

  @Override
  public List<Mark> fetchUnansweredNegativeMarks() {
    return getSession()
        .createCriteria(Mark.class)
        .add(Restrictions.eq("mailSent", false))
        .add(Restrictions.eq("number", 1))
        .list();
  }

  @Override
  public List<Notice> fetchUnansweredNotices() {
    return getSession()
        .createCriteria(Notice.class)
        .add(Restrictions.eq("mailSent", false))
        .list();
  }

  @Override
  public void save(Object object) {
    getSession().save(object);
  }

  private Session getSession() {
    return injector.getInstance(Session.class);
  }
}
