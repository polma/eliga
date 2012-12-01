package pl.wroc.uni.ii.eliga.db;

import java.util.Date;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Notice {
  private final int id;
  private final String desc;
  private final Date date;
  private final int studentId;
  private final int teacherId;

  public Notice(int id, String desc, Date date, int studentId, int teacherId) {
    this.id = id;
    this.desc = desc;
    this.date = date;
    this.studentId = studentId;
    this.teacherId = teacherId;
  }

  public int getId() {
    return id;
  }

  public String getDesc() {
    return desc;
  }

  public Date getDate() {
    return date;
  }

  public int getStudentId() {
    return studentId;
  }

  public int getTeacherId() {
    return teacherId;
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
