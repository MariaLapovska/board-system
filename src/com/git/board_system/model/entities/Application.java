package com.git.board_system.model.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents application, applied by user for a certain faculty, that contains
 * information about his certificate and exams.
 */
public class Application {

	public static final String CERTIFICATE_PATTERN = "^[0-9]{10}$"; // ex.:
																	// 0123456789
	public static final String GRADE_PATTERN = "(^[1][0-9][0-9]$)|(^200$)"; // 100-200

	private int id;
	private User user;
	private Faculty faculty;
	private String certificateNumber;
	private int certificateGrade;

	/** Map with pairs - exam on given subject and grade for it */
	private Map<Subject, Integer> exams = new HashMap<Subject, Integer>();

	public Application() {
	}

	public Application(int id, User user, Faculty faculty,
			String certificateNumber, int certificateGrade) {
		this.id = id;
		this.user = user;
		this.faculty = faculty;
		this.certificateNumber = certificateNumber;
		this.certificateGrade = certificateGrade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public int getCertificateGrade() {
		return certificateGrade;
	}

	public void setCertificateGrade(int certificateGrade) {
		this.certificateGrade = certificateGrade;
	}

	public Map<Subject, Integer> getExams() {
		return exams;
	}

	public void setExams(Map<Subject, Integer> exams) {
		this.exams = exams;
	}

	public void addExam(Subject subject, int grade) {
		exams.put(subject, grade);
	}

	/**
	 * Returns sum of certificate and exams grades.
	 * 
	 * @return sum grade
	 */
	public int getSumGrade() {
		return certificateGrade
				+ exams.values().stream().reduce(0, Integer::sum);
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", user=" + user + ", faculty="
				+ faculty + ", certificateNumber=" + certificateNumber
				+ ", certificateGrade=" + certificateGrade + ", exams=" + exams
				+ "]";
	}
}