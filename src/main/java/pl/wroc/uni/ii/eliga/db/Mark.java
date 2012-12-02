package pl.wroc.uni.ii.eliga.db;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Mark {
  private final int id;
  private final int value;
  private final char sign;
  private final Date date;
  private final int studentId;
  private final int courseId;

  public Mark(int id, int value, char sign, Date date, int studentId, int courseId) {
    this.id = id;
    this.value = value;
    this.sign = sign;
    this.date = date;
    this.studentId = studentId;
    this.courseId = courseId;
  }

  public int getId() {
    return id;
  }

  public int getValue() {
    return value;
  }

  public char getSign() {
    return sign;
  }

  public Date getDate() {
    return date;
  }

  public int getStudentId() {
    return studentId;
  }

  public int getCourseId() {
    return courseId;
  }

  @Override
  public boolean equals(Object o) {
    return reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
