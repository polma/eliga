package pl.wroc.uni.ii.eliga.db;

public class Student {
	private final int id;
	private String name;
	private String surname;
	private String pesel;
	public Student(int id, String name, String surname, String pesel) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
	}
}
