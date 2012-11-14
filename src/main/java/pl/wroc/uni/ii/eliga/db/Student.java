package pl.wroc.uni.ii.eliga.db;

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

}
