package pl.wroc.uni.ii.eliga.db;

public class Course {
	private final int id;
	private final String name;
	private final String desc;
	private final int teacherId;

	public Course(int id, String name, String desc, int teacherId) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.teacherId = teacherId;
	}
}