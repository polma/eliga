package pl.wroc.uni.ii.eliga.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DatabaseService {
  List<Mark> fetchNegativeMarksNewerThan(Date date) throws SQLException;

  List<Notice> fetchNoticesNewerThan(Date date) throws SQLException;

  List<Parent> getParentsFor(Student student) throws SQLException;

  Student getStudentById(int id) throws SQLException;

  Course getCourseById(int id) throws SQLException;

  Teacher getTeacherById(int id) throws SQLException;
}
