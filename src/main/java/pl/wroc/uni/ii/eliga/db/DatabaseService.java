package pl.wroc.uni.ii.eliga.db;

import java.util.Date;
import java.util.List;

public interface DatabaseService {
  List<Mark> fetchNegativeMarksNewerThan(Date date);

  List<Notice> fetchNoticesNewerThan(Date date);

  List<Parent> getParentsFor(Student student);

  Student getStudentFor(Notice notice);

  Student getStudentFor(Mark mark);

  Course getCourseById(int id);

  Teacher getTeacherById(int id);
}
