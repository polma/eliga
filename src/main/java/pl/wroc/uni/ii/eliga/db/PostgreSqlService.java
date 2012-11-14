package pl.wroc.uni.ii.eliga.db;

import java.util.Date;
import java.util.List;

public class PostgreSqlService implements DatabaseService {

  @Override
  public List<Mark> fetchNegativeMarksNewerThan(Date date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Notice> fetchNoticesNewerThan(Date date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Parent> getParentsFor(Student student) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Student getStudentFor(Notice notice) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Student getStudentFor(Mark mark) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Course getCourseById(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Teacher getTeacherById(int id) {
    // TODO Auto-generated method stub
    return null;
  }

}
