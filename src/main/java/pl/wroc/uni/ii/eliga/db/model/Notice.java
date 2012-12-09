package pl.wroc.uni.ii.eliga.db.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

@Entity
public class Notice {
  @Id
  @GeneratedValue
  private int id;

  @Column(nullable = false)
  private String description;

  @Temporal(value = DATE)
  @Column(nullable = false)
  private Date date;

  @ManyToOne(optional = false)
  private Student student;

  @ManyToOne(optional = false)
  private Teacher teacher;

  @Column(nullable = false)
  private boolean mailSent;

  public Notice() {
  }

  public Notice(String description, Date date, Student student, Teacher teacher) {
    this.description = description;
    this.date = date;
    this.student = student;
    this.teacher = teacher;
    this.mailSent = false;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public Date getDate() {
    return date;
  }

  public Student getStudent() {
    return student;
  }

  public Teacher getTeacher() {
    return teacher;
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
