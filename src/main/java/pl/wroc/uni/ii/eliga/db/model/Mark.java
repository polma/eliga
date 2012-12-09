package pl.wroc.uni.ii.eliga.db.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

@Entity
public class Mark {
  @Id
  @GeneratedValue
  private int id;

  @Column(nullable = false)
  private int number;

  private char sign;

  @Temporal(value = DATE)
  @Column(nullable = false)
  private Date date;

  @ManyToOne(optional = false)
  private Student student;

  @ManyToOne(optional = false)
  private Course course;

  @Column(nullable = false)
  private boolean mailSent;

  public Mark() {
  }

  public Mark(int number, char sign, Date date, Student student, Course course) {
    this.number = number;
    this.sign = sign;
    this.date = date;
    this.student = student;
    this.course = course;
    this.mailSent = false;
  }

  public Mark(int number, char sign, Date date, Student student, Course course, boolean sent) {
    this(number, sign, date, student, course);
    this.mailSent = sent;
  }

  public int getId() {
    return id;
  }

  public int getNumber() {
    return number;
  }

  public char getSign() {
    return sign;
  }

  public Date getDate() {
    return date;
  }

  public Student getStudent() {
    return student;
  }

  public Course getCourse() {
    return course;
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
