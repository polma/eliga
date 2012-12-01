package pl.wroc.uni.ii.eliga.db;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Student {
  private final int id;
  private final String name;
  private final String surname;
  private final String pesel;

  public Student(int id, String name, String surname, String pesel) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.pesel = pesel;
  }

  public int getId() {
    return id;
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
}
