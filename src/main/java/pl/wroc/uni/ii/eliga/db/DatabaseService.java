package pl.wroc.uni.ii.eliga.db;

import pl.wroc.uni.ii.eliga.db.model.Mark;
import pl.wroc.uni.ii.eliga.db.model.Notice;
import pl.wroc.uni.ii.eliga.db.model.Student;
import pl.wroc.uni.ii.eliga.db.model.Teacher;

import java.util.List;

public interface DatabaseService {
	
  List<Mark> fetchUnansweredNegativeMarks();

  List<Notice> fetchUnansweredNotices();

  void save(Object object);
  
  public List<Teacher> fetchTeachers();
  
  public Student findStudentByPesel(String pesel);
  
  public List<String> findMailAdresByStudentPesel(String studentPesel);

}
