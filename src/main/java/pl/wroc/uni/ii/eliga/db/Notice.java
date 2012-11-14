package pl.wroc.uni.ii.eliga.db;

import java.util.Date;

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
}