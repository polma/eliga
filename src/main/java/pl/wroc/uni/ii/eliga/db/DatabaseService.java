package pl.wroc.uni.ii.eliga.db;

import java.util.List;

import pl.wroc.uni.ii.eliga.db.model.Mark;
import pl.wroc.uni.ii.eliga.db.model.Notice;
import pl.wroc.uni.ii.eliga.db.model.Student;
import pl.wroc.uni.ii.eliga.db.model.Teacher;

public interface DatabaseService {
	
  List<Mark> fetchUnansweredNegativeMarks();

  List<Notice> fetchUnansweredNotices();

  void save(Object object);
  
  public List<Teacher> fetchTeachers();
  
  public Student findStudentByPesel(String pesel);
  
  public String findMailAdresByStudentPesel(String studentPesel);

}
