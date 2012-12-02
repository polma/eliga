package pl.wroc.uni.ii.eliga.db;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.addDays;
import static org.fest.assertions.Assertions.assertThat;
import static pl.wroc.uni.ii.eliga.common.InjectionConfiguration.getInjector;
import static pl.wroc.uni.ii.eliga.db.TestObjects.*;

public class DatabaseServiceTest extends SqlBasicTest {
  private DatabaseService db = getInjector().getInstance(DatabaseService.class);

  @Test
  public void retrievesValidStudentFromDb() throws SQLException {
    insertStudent();

    assertThat(db.getStudentById(student().getId())).isEqualTo(student());
  }

  @Test
  public void retrievesValidTeacherFromDb() throws SQLException {
    insertTeacher();

    assertThat(db.getTeacherById(teacher().getId())).isEqualTo(teacher());
  }

  @Test
  public void retrievesValidCourseFromDb() throws SQLException {
    insertCourse();

    assertThat(db.getCourseById(course().getId())).isEqualTo(course());
  }

  @Test
  public void retrievesValidParentFromDb() throws SQLException {
    insertParent();

    assertThat(db.getParentsFor(student())).containsExactly(parent());
  }

  @Test
  public void retrievesValidNoticeFromDb() throws SQLException {
    insertNotice();

    assertThat(db.fetchNoticesNewerThan(dayBefore(notice().getDate()))).containsExactly(notice());
  }

  @Test
  public void retrievesNegativeMarkFromDb() throws SQLException {
    insertMark(negativeMark());

    assertThat(db.fetchNegativeMarksNewerThan(dayBefore(negativeMark().getDate()))).containsExactly(negativeMark());
  }

  @Test
  public void doNotRetrieveNonNegativeMark() throws SQLException {
    insertMark(positiveMark());

    assertThat(db.fetchNegativeMarksNewerThan(dayBefore(negativeMark().getDate()))).isEmpty();
  }

  private Date dayBefore(Date date) {
    return addDays(date, -1);
  }
}
