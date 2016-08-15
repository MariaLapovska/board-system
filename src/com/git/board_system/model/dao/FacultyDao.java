package com.git.board_system.model.dao;

import java.util.List;

import com.git.board_system.model.entities.Faculty;
import com.git.board_system.model.entities.Subject;

/**
 * Provides interface with necessary methods for working with Faculty objects
 * and database.
 * 
 * @see Faculty
 */
public interface FacultyDao extends GenericDao<Faculty> {

	/**
	 * Returns list of subjects that need to be passed to apply for faculty with
	 * given id
	 * 
	 * @param id
	 *            Index of faculty
	 * @return List of faculty subjects
	 */
	List<Subject> getSubjects(int id);

	/**
	 * Inserts subject with given id to faculty with given id
	 * 
	 * @param facultyId
	 *            Index of faculty
	 * @param subjectId
	 *            Index of subject
	 * @return Index of faculty-subject pair in table, negative value if pair
	 *         wasn't inserted
	 */
	int insertSubject(int facultyId, int subjectId);
}