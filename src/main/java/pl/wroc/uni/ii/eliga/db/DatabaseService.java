package pl.wroc.uni.ii.eliga.db;

import pl.wroc.uni.ii.eliga.db.model.Mark;
import pl.wroc.uni.ii.eliga.db.model.Notice;

import java.util.List;

public interface DatabaseService {
  List<Mark> fetchUnansweredNegativeMarks();

  List<Notice> fetchUnansweredNotices();

  void save(Object object);
}
