package pl.wroc.uni.ii.eliga.db;

import java.util.Date;

public class Mark {
	private final int id;
	private final int value;
	private final char sign;
	private final Date date;
	private final int studentId;
	private final int teacherId;
	private final int courseId;
	
	public Mark(int id, int value, char sign, Date date, int studentId,
			int teacherId, int courseId) {
		this.id = id;
		this.value = value;
		this.sign = sign;
		this.date = date;
		this.studentId = studentId;
		this.teacherId = teacherId;
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
	public int getTeacherId() {
		return teacherId;
	}
	public int getCourseId() {
		return courseId;
	}
	
}
