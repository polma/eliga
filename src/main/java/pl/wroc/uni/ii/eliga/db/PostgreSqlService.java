package pl.wroc.uni.ii.eliga.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostgreSqlService implements DatabaseService {
  private final PostgreSqlConnector connector;

  public PostgreSqlService(PostgreSqlConnector connector) {
    this.connector = connector;
  }

  @Override
  public List<Mark> fetchNegativeMarksNewerThan(Date date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Notice> fetchNoticesNewerThan(Date date) throws SQLException {
    PreparedStatement statement = connector.prepareStatement("SELECT * FROM uwagi WHERE data > ?");
    statement.setDate(1, new java.sql.Date(date.getTime()));
    ResultSet result = statement.executeQuery();

    List<Notice> notices = new ArrayList<Notice>();
    while (result.next()) {
      notices.add(new Notice(result.getInt("id"), result.getString("tresc"), result.getDate("data"), result.getInt("id_ucznia"), result
          .getInt("id_nauczyciela")));
    }

    return notices;
  }

  @Override
  public List<Parent> getParentsFor(Student student) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Student getStudentFor(Notice notice) throws SQLException {
    return getStudentById(notice.getStudentId());
  }

  @Override
  public Student getStudentFor(Mark mark) {
    // TODO Auto-generated method stub
    return null;
  }

  private Student getStudentById(int id) throws SQLException {
    ResultSet result = getNonEmptyResult("SELECT * FROM uczniowie WHERE id = " + id);
    return new Student(id, result.getString("imie"), result.getString("nazwisko"), result.getString("pesel"));
  }

  @Override
  public Course getCourseById(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Teacher getTeacherById(int id) throws SQLException {
    ResultSet result = getNonEmptyResult("SELECT * FROM nauczyciele WHERE id = " + id);
    return new Teacher(id, result.getString("imie"), result.getString("nazwisko"));
  }

  private ResultSet getNonEmptyResult(String sql) throws SQLException {
    ResultSet result = connector.execute(sql);
    if (!result.next()) {
      throw new SQLException("Empty result returned for SQL: " + sql);
    }
    return result;
  }
}
