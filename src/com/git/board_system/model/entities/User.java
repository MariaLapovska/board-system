package com.git.board_system.model.entities;

/**
 * Represents user of the board system that can be an enrollee or admin as well.
 * User has certain login, password, name, surname and role.
 */
public class User {

	/** User roles */
	public enum Role {
		ENROLLEE, ADMIN
	}

	public static final String LOGIN_PATTERN = "^\\S{5,20}$"; // ex.: lama_m
	public static final String NAME_PATTERN = "^[a-zA-Z ,.'-]{3,25}$"; // ex.:
																		// Maria-Elisa

	private int id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private Role role;

	public User() {
	}

	public User(int id, String login, String password, String name,
			String surname, Role role) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", name=" + name + ", surname=" + surname + ", role=" + role
				+ "]";
	}
}