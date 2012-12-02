package pl.wroc.uni.ii.eliga.db;

import pl.wroc.uni.ii.eliga.db.model.*;

import java.text.ParseException;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

public class TestObjects {
  public static Mark positiveMark() {
    return markWith(7, 2);
  }

  public static Mark negativeMark() {
    return markWith(6, 1);
  }

  public static Mark markWith(int id, int value) {
    return new Mark(id, value, '-', date("12.12.2012"), student().getId(), course().getId());
  }

  public static Parent parent() {
    return new Parent(5, "jan", "kowalsky", "mail@mail.com", "123", student().getId());
  }

  public static Course course() {
    return new Course(4, "algebra", "opis", teacher().getId());
  }

  public static Notice notice() {
    return new Notice(3, "Naganne zachowanie", date("12.12.2012"), student().getId(), teacher().getId());
  }

  public static Teacher teacher() {
    return new Teacher(2, "Nauczyciel", "testowy");
  }

  public static Student student() {
    return new Student(1, "Adam", "Testowy", "95110411995");
  }

  public static Date date(String date) {
    try {
      return parseDate(date, "MM.dd.yyyy");
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
}
