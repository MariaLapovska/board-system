package com.git.board_system.model.services;

import java.util.List;

import com.git.board_system.model.dao.DaoFactory;
import com.git.board_system.model.dao.DaoFactoryType;
import com.git.board_system.model.dao.GenericDao;
import com.git.board_system.model.entities.Subject;

/**
 * Provides methods for working with Subject objects and database for Controller
 * 
 * @see Subject
 * @see GenericDao
 */
public class SubjectService {

	private static final SubjectService instance = new SubjectService();

	private SubjectService() {
	}

	public static SubjectService getInstance() {
		return instance;
	}

	/**
	 * Returns all subjects from database
	 * 
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return List of all subjects
	 */
	public List<Subject> getAll(DaoFactoryType factoryType) {
		GenericDao<Subject> subjectDao = DaoFactory.getFactory(factoryType)
				.createSubjectDao();

		return subjectDao.getAll(-1, -1);
	}

	/**
	 * Finds subject with given index in database
	 * 
	 * @param id
	 *            Index of subject to find
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Subject with given index, null if nothing was found
	 */
	public Subject find(int id, DaoFactoryType factoryType) {
		GenericDao<Subject> subjectDao = DaoFactory.getFactory(factoryType)
				.createSubjectDao();

		return subjectDao.find(id);
	}
}
