package pl.wroc.uni.ii.eliga.db.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

@Entity
public class Student {
  public static final int PESEL_NUMBER_LENGTH = 11;

  @Id
  @Column(length = PESEL_NUMBER_LENGTH)
  private String pesel;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

  @ManyToMany
  private Set<Parent> parents;

  public Student() {
  }

  public Student(String name, String surname, String pesel, Set<Parent> parents) {
    this.name = name;
    this.surname = surname;
    this.pesel = pesel;
    this.parents = parents;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getPesel() {
    return pesel;
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
