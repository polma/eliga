package pl.wroc.uni.ii.eliga.db;

import org.junit.Before;
import pl.wroc.uni.ii.eliga.db.model.Mark;

import java.sql.SQLException;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static pl.wroc.uni.ii.eliga.common.InjectionConfiguration.getInjector;
import static pl.wroc.uni.ii.eliga.db.TestObjects.*;

public class SqlBasicTest {
  protected DatabaseConnector connector = getInjector().getInstance(DatabaseConnector.class);

  @Before
  public void cleanUpDatabase() throws SQLException {
    for (String table : asList("nauczyciele", "uczniowie", "uwagi", "oceny", "przedmioty")) {
      connector.executeUpdate(format("TRUNCATE TABLE %s CASCADE", table));
    }
  }

  protected void insertMark(Mark mark) throws SQLException {
    insertStudent();
    insertCourse();

    connector.executeUpdate(format("INSERT INTO oceny VALUES (%d, %d, '%s', '%s', %s, %s)",
        mark.getId(), mark.getValue(), mark.getSign(), mark.getDate(), mark.getStudentId(), mark.getCourseId()));
  }

  protected void insertParent() throws SQLException {
    insertStudent();

    connector.executeUpdate(format("INSERT INTO rodzice VALUES (%d, '%s', '%s', '%s', '%s', %d)",
        parent().getId(), parent().getName(), parent().getSurname(), parent().getEmail(), parent().getPhone(), parent().getStudentId()));
  }

  protected void insertCourse() throws SQLException {
    insertTeacher();

    connector.executeUpdate(format("INSERT INTO przedmioty VALUES (%d, '%s', '%s', %d)",
        course().getId(), course().getName(), course().getDesc(), course().getTeacherId()));
  }

  protected void insertNotice() throws SQLException {
    insertStudent();
    insertTeacher();

    connector.executeUpdate(format("INSERT INTO uwagi VALUES (%d, '%s', '%s', %d, %d)",
        notice().getId(), notice().getDesc(), notice().getDate(), notice().getStudentId(), notice().getTeacherId()));
  }

  protected void insertTeacher() throws SQLException {
    connector.executeUpdate(format("INSERT INTO nauczyciele VALUES (%d, '%s', '%s')",
        teacher().getId(), teacher().getName(), teacher().getSurname()));
  }

  protected void insertStudent() throws SQLException {
    connector.executeUpdate(format("INSERT INTO uczniowie VALUES (%d, '%s', '%s', '%s')",
        student().getId(), student().getName(), student().getSurname(), student().getPesel()));
  }
}
