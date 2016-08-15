package com.git.board_system.model.entities;

/**
 * Represents subject with a certain name.
 */
public class Subject {

	public static final String NAME_PATTERN = "^[a-zA-Z ,.'-]{3,50}$"; // ex.:
																		// Math

	private int id;
	private String name;

	public Subject() {
	}

	public Subject(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Subject other = (Subject) obj;
		return (id == other.id) && (name.equals(other.name));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + id;
		result = prime * result + name.hashCode();

		return result;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + "]";
	}
}