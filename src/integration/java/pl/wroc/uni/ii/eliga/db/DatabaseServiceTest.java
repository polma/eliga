package pl.wroc.uni.ii.eliga.db;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.wroc.uni.ii.eliga.common.Transactional;
import pl.wroc.uni.ii.eliga.db.model.*;
import pl.wroc.uni.ii.eliga.test.util.GuiceJUnitRunner;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static pl.wroc.uni.ii.eliga.test.util.TestObjects.*;

@RunWith(GuiceJUnitRunner.class)
public class DatabaseServiceTest {
  @Inject
  Injector injector;
  @Inject
  DatabaseService databaseService;

  @Before
  @Transactional
  public void cleanUp() {
    for (Class clazz : asList(Course.class, Mark.class, Notice.class, Parent.class, Student.class, Teacher.class)) {
      String tableName = clazz.getSimpleName();
      getSession().createSQLQuery(format("TRUNCATE table %s CASCADE", tableName)).executeUpdate();
    }
  }

  @Test
  @Transactional
  public void fetchesUnansweredNegativeMarkOnly() {
    insertAll();

    List<Mark> marks = databaseService.fetchUnansweredNegativeMarks();
    assertThat(marks).containsExactly(NEGATIVE_MARK_UNANSWERED);
  }

  @Test
  @Transactional
  public void fetchesUnansweredNotices() {
    insertAll();

    List<Notice> notices = databaseService.fetchUnansweredNotices();
    assertThat(notices).containsExactly(NOTICE);
  }

  @Transactional
  @Test(expected = GenericJDBCException.class)
  public void throwsAnExceptionForNotCapitalizedTeachersSurname() {
    try {
      databaseService.save(new Teacher("A", "b"));
    } finally {
      assertThat(selectAll(Teacher.class)).isEmpty();
    }
  }

  @Test
  @Transactional
  public void fetchesParentMailsByStudentPesel() {
    insertAll();

    List<String> mails = databaseService.findMailAdresByStudentPesel(STUDENT.getPesel());

    assertThat(mails).containsOnly(PARENT.getEmail());
  }

  public void insertAll() {
    databaseService.save(TEACHER);
    databaseService.save(PARENT);
    databaseService.save(STUDENT);
    databaseService.save(NOTICE);
    databaseService.save(COURSE);
    databaseService.save(POSTIIVE_MARK);
    databaseService.save(NEGATIVE_MARK_UNANSWERED);
    databaseService.save(NEGATIVE_MARK_WITH_ANSWER);

    assertThat(selectAll(Notice.class)).isNotEmpty();
    assertThat(selectAll(Mark.class)).hasSize(3);
  }

  private List selectAll(Class clazz) {
    return getSession().createCriteria(clazz).list();
  }

  private Session getSession() {
    return injector.getInstance(Session.class);
  }
}
