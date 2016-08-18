package com.git.board_system.model.dao;

import java.util.List;
import java.util.Map;

import com.git.board_system.model.entities.Application;
import com.git.board_system.model.entities.Subject;

/**
 * Provides interface with necessary methods for working with Application
 * objects and database.
 * 
 * @see Application
 */
public interface ApplicationDao extends GenericDao<Application> {

	/**
	 * Finds application by id of user that applied it
	 * 
	 * @param userId
	 *            Index of user to search by
	 * @return Application, applied by user with given id, null if nothing was
	 *         found
	 */
	Application findByUser(int userId);

	/**
	 * Returns given quantity of applications, applied for faculty with given
	 * id, starting from given index. If from and/or quantity values are
	 * negative, gets all applications for given faculty.
	 * 
	 * @param from
	 *            Index from which to get applications
	 * @param quantity
	 *            Quantity of applications to get
	 * @param facultyId
	 *            Index of faculty to search by
	 * @return List of gotten applications
	 */
	List<Application> getByFaculty(int from, int quantity, int facultyId);

	/**
	 * Returns total number of applications, applied for faculty with given id.
	 * If given id is negative, returns total number of all applications.
	 * 
	 * @param facultyId
	 *            Index of faculty
	 * @return Total number of applications for given faculty
	 */
	int getApplicationsNumber(int facultyId);

	/**
	 * Returns map with pairs subject-grade, passed by user with given
	 * application id
	 * 
	 * @param id
	 *            Index of application to search by
	 * @return Map with pairs subject-grade from application
	 */
	Map<Subject, Integer> getExams(int id);

	/**
	 * Inserts subject with given id and given grade to application with given
	 * id
	 * 
	 * @param applicationId
	 *            Index of application
	 * @param subjectId
	 *            Index of subject
	 * @param grade
	 *            Grade for given subject
	 * @return Index of created record in table, negative value if record wasn't
	 *         inserted
	 */
	int insertExam(int applicationId, int subjectId, int grade);
	
	/**
	 * Deletes exams from application with given id
	 * 
	 * @param applicationId Index of application to search by
	 * @return boolean value, indicating if subjects were deleted
	 */
	boolean deleteExams(int applicationId);
}