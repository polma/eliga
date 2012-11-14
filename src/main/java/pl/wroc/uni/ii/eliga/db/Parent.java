package pl.wroc.uni.ii.eliga.db;

public class Parent {
	private final int id;
	private final String name;
	private final String surname;
	private final String email;
	private final String phone;
	private final int studentId;
	
	public Parent(int id, String name, String surname, String email,
			String phone, int studentId) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.studentId = studentId;
	}
}
