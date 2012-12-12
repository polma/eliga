package pl.wroc.uni.ii.eliga.test.util;

import pl.wroc.uni.ii.eliga.db.model.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.time.DateUtils.parseDate;

public class TestObjects {
  public static final Date TEST_DATE = date("12.12.2012");
  public static final Parent PARENT = new Parent("jan", "kowalsky", "mail@mail.com", "123");
  public static final Student STUDENT = new Student("Adam", "Testowy", "95110411995", new HashSet<Parent>(asList(PARENT)));
  public static final Teacher TEACHER = new Teacher("Nauczyciel", "Testowy");
  public static final Notice NOTICE = new Notice("Naganne zachowanie", TEST_DATE, STUDENT, TEACHER);
  public static final Course COURSE = new Course("algebra", "opis", TEACHER);
  public static final Mark NEGATIVE_MARK_UNANSWERED = new Mark(1, '-', TEST_DATE, STUDENT, COURSE);
  public static final Mark NEGATIVE_MARK_WITH_ANSWER = new Mark(1, '-', TEST_DATE, STUDENT, COURSE, true);
  public static final Mark POSTIIVE_MARK = new Mark(5, '-', TEST_DATE, STUDENT, COURSE);

  public static Date date(String date) {
    try {
      return parseDate(date, "MM.dd.yyyy");
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
}
