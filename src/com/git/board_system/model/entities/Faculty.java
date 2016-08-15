package com.git.board_system.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents faculty with a certain name, recruitment plan and set of subjects
 * that have to be passed to apply for it.
 */
public class Faculty {

	public static final String NAME_PATTERN = "^[a-zA-Z ,.'-]{3,50}$"; // ex.:
																		// Engineering
	public static final String PLAN_PATTERN = "^[1-3][0-9]$"; // 10-39

	private int id;
	private String name;
	private int plan;

	/** Indicates if faculty is still available to apply */
	private boolean isAvailable;

	/** List of subjects that have to be passed to apply for faculty */
	private List<Subject> subjects = new ArrayList<Subject>();

	public Faculty() {
	}

	public Faculty(int id, String name, int plan, boolean isAvailable) {
		this.id = id;
		this.name = name;
		this.plan = plan;
		this.isAvailable = isAvailable;
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

	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public void addSubject(Subject subject) {
		subjects.add(subject);
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + ", plan=" + plan
				+ ", isAvailable=" + isAvailable + ", subjects=" + subjects
				+ "]";
	}
}