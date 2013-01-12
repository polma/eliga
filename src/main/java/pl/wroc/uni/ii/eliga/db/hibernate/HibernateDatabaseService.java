package pl.wroc.uni.ii.eliga.db.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pl.wroc.uni.ii.eliga.db.DatabaseService;
import pl.wroc.uni.ii.eliga.db.model.Mark;
import pl.wroc.uni.ii.eliga.db.model.Notice;
import pl.wroc.uni.ii.eliga.db.model.Student;
import pl.wroc.uni.ii.eliga.db.model.Teacher;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

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
  public List<Teacher> fetchTeachers() {
    return getSession()
        .createCriteria(Teacher.class)
        .list();
  }
  
  @Override
  public Student findStudentByPesel(String pesel) {
    return (Student) getSession()
        .createCriteria(Student.class)
        .add(Restrictions.eq("pesel", pesel)).uniqueResult();
  }

  public String findMailAdresByStudentPesel(String studentPesel) {
	  return "dawid.ryznar@yahoo.com"; 
  }
  
  @Override
  public void save(Object object) {
    getSession().save(object);
  }

  private Session getSession() {
    return injector.getInstance(Session.class);
  }

}
