package pl.wroc.uni.ii.eliga.db;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static java.lang.String.format;
import static org.apache.commons.lang3.time.DateUtils.addDays;
import static org.apache.commons.lang3.time.DateUtils.parseDate;
import static org.fest.assertions.Assertions.assertThat;

public class PostgreSqlServiceTest extends SqlBasicTest {
  private DatabaseService db;

  @Before
  public void setUp() {
    db = new PostgreSqlService(connector);
  }

  @Test
  public void databaseServiceGetsValidInformationFromDatabase() throws SQLException, ParseException {
    insertStudent();
    insertTeacher();
    insertNotice();
    insertCourse();
    insertParent();
    insertMark(negativeMark());
    insertMark(positiveMark());

    assertThat(db.fetchNoticesNewerThan(dayBefore(notice().getDate()))).containsExactly(notice());
    assertThat(db.getStudentById(student().getId())).isEqualTo(student());
    assertThat(db.getTeacherById(teacher().getId())).isEqualTo(teacher());
    assertThat(db.getCourseById(course().getId())).isEqualTo(course());
    assertThat(db.getParentsFor(student())).containsExactly(parent());
    assertThat(db.fetchNegativeMarksNewerThan(dayBefore(negativeMark().getDate()))).containsExactly(negativeMark());
  }

  private void insertMark(Mark mark) throws SQLException {
    connector.executeUpdate(format("INSERT INTO oceny VALUES (%d, %d, '%s', '%s', %s, %s)",
        mark.getId(), mark.getValue(), mark.getSign(), mark.getDate(), mark.getStudentId(), mark.getCourseId()));
  }

  private void insertParent() throws SQLException {
    connector.executeUpdate(format("INSERT INTO rodzice VALUES (%d, '%s', '%s', '%s', '%s', %d)",
        parent().getId(), parent().getName(), parent().getSurname(), parent().getEmail(), parent().getPhone(), parent().getStudentId()));
  }

  private void insertCourse() throws SQLException {
    connector.executeUpdate(format("INSERT INTO przedmioty VALUES (%d, '%s', '%s', %d)",
        course().getId(), course().getName(), course().getDesc(), course().getTeacherId()));
  }

  private void insertNotice() throws SQLException, ParseException {
    connector.executeUpdate(format("INSERT INTO uwagi VALUES (%d, '%s', '%s', %d, %d)",
        notice().getId(), notice().getDesc(), notice().getDate(), notice().getStudentId(), notice().getTeacherId()));
  }

  private void insertTeacher() throws SQLException {
    connector.executeUpdate(format("INSERT INTO nauczyciele VALUES (%d, '%s', '%s')",
        teacher().getId(), teacher().getName(), teacher().getSurname()));
  }

  private void insertStudent() throws SQLException {
    connector.executeUpdate(format("INSERT INTO uczniowie VALUES (%d, '%s', '%s', '%s')",
        student().getId(), student().getName(), student().getSurname(), student().getPesel()));
  }

  private Date dayBefore(Date date) throws ParseException {
    return addDays(date, -1);
  }

  private Mark positiveMark() throws ParseException {
    return markWith(7, 2);
  }

  private Mark negativeMark() throws ParseException {
    return markWith(6, 1);
  }

  private Mark markWith(int id, int value) throws ParseException {
    return new Mark(id, value, '-', date("12.12.2012"), student().getId(), course().getId());
  }

  private Parent parent() {
    return new Parent(5, "jan", "kowalsky", "mail@mail.com", "123", student().getId());
  }

  private Course course() {
    return new Course(4, "algebra", "opis", teacher().getId());
  }

  private Notice notice() throws ParseException {
    return new Notice(3, "Naganne zachowanie", date("12.12.2012"), student().getId(), teacher().getId());
  }

  private Teacher teacher() {
    return new Teacher(2, "Nauczyciel", "testowy");
  }

  private Student student() {
     return new Student(1, "Adam", "Testowy", "95110411995");
  }

  private Date date(String date) throws ParseException {
    return parseDate(date, "MM.dd.yyyy");
  }
}
