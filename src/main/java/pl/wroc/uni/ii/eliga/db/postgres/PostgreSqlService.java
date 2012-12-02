package pl.wroc.uni.ii.eliga.db.postgres;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.wroc.uni.ii.eliga.db.DatabaseConnector;
import pl.wroc.uni.ii.eliga.db.DatabaseService;
import pl.wroc.uni.ii.eliga.db.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class PostgreSqlService implements DatabaseService {
  @Inject
  private DatabaseConnector connector;

  @Override
  public List<Mark> fetchNegativeMarksNewerThan(Date date) throws SQLException {
    PreparedStatement statement = connector.prepareStatement("SELECT * FROM oceny WHERE wartosc = 1 AND data > ?");
    statement.setDate(1, new java.sql.Date(date.getTime()));
    ResultSet result = statement.executeQuery();

    List<Mark> marks = new ArrayList<Mark>();
    while (result.next()) {
      marks.add(new Mark(result.getInt("id"), result.getInt("wartosc"), result.getString("znak").charAt(0), result.getDate("data"),
          result.getInt("id_ucznia"), result.getInt("id_przedmiotu")));
    }

    return marks;
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
  public List<Parent> getParentsFor(Student student) throws SQLException {
    ResultSet result = connector.execute("SELECT * FROM rodzice WHERE id_ucznia = " + student.getId());

    List<Parent> parents = new ArrayList<Parent>();
    while (result.next()) {
      parents.add(new Parent(result.getInt("id"), result.getString("imie"), result.getString("nazwisko"),
          result.getString("email"), result.getString("tel"), student.getId()));
    }

    return parents;
  }

  @Override
  public Student getStudentById(int id) throws SQLException {
    return studentFrom(fetchSingleResult("SELECT * FROM uczniowie WHERE id = " + id));
  }

  private Student studentFrom(ResultSet result) throws SQLException {
    return new Student(result.getInt("id"), result.getString("imie"), result.getString("nazwisko"), result.getString("pesel"));
  }

  @Override
  public Course getCourseById(int id) throws SQLException {
    return courseFrom(fetchSingleResult("SELECT * FROM przedmioty WHERE id = " + id));
  }

  private Course courseFrom(ResultSet result) throws SQLException {
    return new Course(result.getInt("id"), result.getString("nazwa"), result.getString("opis"), result.getInt("id_nauczyciela"));
  }

  @Override
  public Teacher getTeacherById(int id) throws SQLException {
    return teacherFrom(fetchSingleResult("SELECT * FROM nauczyciele WHERE id = " + id));
  }

  private Teacher teacherFrom(ResultSet result) throws SQLException {
    return new Teacher(result.getInt("id"), result.getString("imie"), result.getString("nazwisko"));
  }

  private ResultSet fetchSingleResult(String sql) throws SQLException {
    ResultSet result = connector.execute(sql);
    if (!result.next()) {
      throw new SQLException("Empty result returned for SQL: " + sql);
    }
    return result;
  }
}
