package pl.wroc.uni.ii.eliga.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DatabaseService {
  List<Mark> fetchNegativeMarksNewerThan(Date date);

  List<Notice> fetchNoticesNewerThan(Date date) throws SQLException;

  List<Parent> getParentsFor(Student student);

  Student getStudentFor(Notice notice) throws SQLException;

  Student getStudentFor(Mark mark);

  Course getCourseById(int id);

  Teacher getTeacherById(int id) throws SQLException;
}
