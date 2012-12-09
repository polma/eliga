package pl.wroc.uni.ii.eliga.db.model;

import javax.persistence.*;
import java.sql.Timestamp;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

@Entity
public class Course {

  @Id
  @GeneratedValue
  private int id;

  @Column(nullable = false)
  private String name;

  private String description;

  Timestamp timer;

  @ManyToOne(optional = false)
  private Teacher teacher;

  public Course() {
  }

  public Course(String name, String description, Teacher teacher) {
    this.name = name;
    this.teacher = teacher;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
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
}
