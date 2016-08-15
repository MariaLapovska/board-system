package com.git.board_system.model.services;

import java.util.List;

import com.git.board_system.model.dao.DaoFactory;
import com.git.board_system.model.dao.DaoFactoryType;
import com.git.board_system.model.dao.FacultyDao;
import com.git.board_system.model.entities.Faculty;
import com.git.board_system.model.entities.Subject;

/**
 * Provides methods for working with Faculty objects and database for Controller
 * 
 * @see Faculty
 * @see FacultyDao
 */
public class FacultyService {

	private static final FacultyService instance = new FacultyService();

	private FacultyService() {
	}

	public static FacultyService getInstance() {
		return instance;
	}

	/**
	 * Adds new faculty to database
	 * 
	 * @param faculty
	 *            Faculty to add
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Index of faculty in database, negative number if it wasn't
	 *         inserted
	 */
	public int addFaculty(Faculty faculty, DaoFactoryType factoryType) {
		FacultyDao facultyDao = DaoFactory.getFactory(factoryType)
				.createFacultyDao();

		return facultyDao.insert(faculty);
	}

	/**
	 * Closes applications submission to the faculty
	 * 
	 * @param faculty
	 *            Faculty to close
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return boolean value, indicating if faculty was closed
	 */
	public boolean closeFaculty(Faculty faculty, DaoFactoryType factoryType) {
		FacultyDao facultyDao = DaoFactory.getFactory(factoryType)
				.createFacultyDao();
		faculty.setAvailable(false);

		return facultyDao.update(faculty);
	}

	/**
	 * Returns all faculties from database
	 * 
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return List of all faculties
	 */
	public List<Faculty> getAll(DaoFactoryType factoryType) {
		FacultyDao facultyDao = DaoFactory.getFactory(factoryType)
				.createFacultyDao();

		return facultyDao.getAll(-1, -1);
	}

	/**
	 * Returns given quantity of faculties, starting from given index
	 * 
	 * @param from
	 *            Index from which to get faculties
	 * @param quantity
	 *            Quantity of faculties to get
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return List of gotten faculties
	 */
	public List<Faculty> getAll(int from, int quantity,
			DaoFactoryType factoryType) {
		FacultyDao facultyDao = DaoFactory.getFactory(factoryType)
				.createFacultyDao();

		return facultyDao.getAll(from, quantity);
	}

	/**
	 * Finds faculty with given index in database
	 * 
	 * @param id
	 *            Index of faculty to find
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Faculty with given index, null if nothing was found
	 */
	public Faculty find(int id, DaoFactoryType factoryType) {
		FacultyDao facultyDao = DaoFactory.getFactory(factoryType)
				.createFacultyDao();

		return facultyDao.find(id);
	}

	/**
	 * Finds faculty with given name
	 * 
	 * @param name
	 *            Name to search by
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Faculty with given name, null if nothing was found
	 */
	public Faculty findByName(String name, DaoFactoryType factoryType) {
		FacultyDao facultyDao = DaoFactory.getFactory(factoryType)
				.createFacultyDao();

		return facultyDao.findByParameter(name);
	}

	/**
	 * Returns list of subjects that need to be passed to apply for faculty with
	 * given id
	 * 
	 * @param id
	 *            Index of faculty
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return List of faculty subjects
	 */
	public List<Subject> getSubjects(int id, DaoFactoryType factoryType) {
		FacultyDao facultyDao = DaoFactory.getFactory(factoryType)
				.createFacultyDao();

		return facultyDao.getSubjects(id);
	}

	/**
	 * Inserts subject with given id to faculty with given id
	 * 
	 * @param faculty
	 *            Faculty to insert into
	 * @param subject
	 *            Subject to insert
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Index of faculty-subject pair in table, negative value if pair
	 *         wasn't inserted
	 */
	public int insertSubject(Faculty faculty, Subject subject,
			DaoFactoryType factoryType) {
		FacultyDao facultyDao = DaoFactory.getFactory(factoryType)
				.createFacultyDao();

		return facultyDao.insertSubject(faculty.getId(), subject.getId());
	}
}