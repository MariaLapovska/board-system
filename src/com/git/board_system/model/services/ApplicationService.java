package com.git.board_system.model.services;

import java.util.List;
import java.util.Map;

import com.git.board_system.model.dao.ApplicationDao;
import com.git.board_system.model.dao.DaoFactory;
import com.git.board_system.model.dao.DaoFactoryType;
import com.git.board_system.model.entities.Application;
import com.git.board_system.model.entities.Subject;
import com.git.board_system.model.entities.User;

/**
 * Provides methods for working with Application objects and database for
 * Controller
 * 
 * @see Application
 * @see ApplicationDao
 */
public class ApplicationService {

	private static final ApplicationService instance = new ApplicationService();

	private ApplicationService() {
	}

	public static ApplicationService getInstance() {
		return instance;
	}

	/**
	 * Adds new application to database
	 * 
	 * @param application
	 *            Application to add
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Index of application in database, negative number if it wasn't
	 *         inserted
	 */
	public int addApplication(Application application,
			DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.insert(application);
	}

	/**
	 * Edits application in database
	 * 
	 * @param application
	 *            Application to edit
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return boolean value, indicating if application was edited
	 */
	public boolean editApplication(Application application,
			DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.update(application);
	}

	/**
	 * Deletes application from database
	 * 
	 * @param application
	 *            Application to delete
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return boolean value, indicating if application was deleted
	 */
	public boolean deleteApplication(Application application,
			DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.delete(application.getId());
	}

	/**
	 * Returns all applications from database
	 * 
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return List of all applications
	 */
	public List<Application> getAll(DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.getAll(-1, -1);
	}

	/**
	 * Returns given quantity of applications, starting from given index
	 * 
	 * @param from
	 *            Index from which to get applications
	 * @param quantity
	 *            Quantity of applications to get
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return List of gotten applications
	 */
	public List<Application> getAll(int from, int quantity,
			DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.getAll(from, quantity);
	}

	/**
	 * Finds application with given index in database
	 * 
	 * @param id
	 *            Index of application to find
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Application with given index, null if nothing was found
	 */
	public Application find(int id, DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.find(id);
	}

	/**
	 * Finds application with given certificate number
	 * 
	 * @param certificate
	 *            Certificate number to search by
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Application with given certificate number, null if nothing was
	 *         found
	 */
	public Application findByCertificate(String certificate,
			DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.findByParameter(certificate);
	}

	/**
	 * Finds application, applied by given user
	 * 
	 * @param user
	 *            User to search by
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Application, applied by given user, null if nothing was found
	 */
	public Application findByUser(User user, DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.findByUser(user.getId());
	}

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
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return List of gotten applications
	 */
	public List<Application> getByFaculty(int from, int quantity,
			int facultyId, DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.getByFaculty(from, quantity, facultyId);
	}

	/**
	 * Returns index of application in list of applications applied to same
	 * faculty
	 * 
	 * @param application
	 *            Application to get index from
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Index of application in list of applications applied to same
	 *         faculty, negative value if it wasn't found
	 */
	public int getApplicationIndex(Application application,
			DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();
		List<Application> applications = applicationDao.getByFaculty(-1, -1,
				application.getFaculty().getId());
		int size = applications.size();

		for (int i = 0; i < size; i++) {
			if (applications.get(i).getId() == application.getId()) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns total number of applications
	 * 
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Total number of applications
	 */
	public int getApplicationsNumber(DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.getApplicationsNumber(-1);
	}

	/**
	 * Returns total number of applications, applied for faculty with given id.
	 * 
	 * @param facultyId
	 *            Index of faculty
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Returns total number of applications, applied for faculty with
	 *         given id.
	 */
	public int getApplicationsNumber(int facultyId, DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.getApplicationsNumber(facultyId);
	}

	/**
	 * Returns map with pairs subject-grade, passed by user with given
	 * application
	 * 
	 * @param application
	 *            Application to search by
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Map with pairs subject-grade from application
	 */
	public Map<Subject, Integer> getExams(Application application,
			DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.getExams(application.getId());
	}

	/**
	 * Inserts given subject and given grade to given application
	 * 
	 * @param application
	 *            Application to insert into
	 * @param subject
	 *            Subject to insert
	 * @param grade
	 *            Grade to insert
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Index of created record in table, negative value if record wasn't
	 *         inserted
	 */
	public int insertExam(Application application, Subject subject, int grade,
			DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.insertExam(application.getId(), subject.getId(),
				grade);
	}

	/**
	 * Updates exam from given application
	 * 
	 * @param application
	 *            Application to update exam from
	 * @param subject
	 *            Subject to set
	 * @param grade
	 *            Grade to set
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return boolean value, indicating if update was made
	 */
	public boolean updateExam(Application application, Subject subject,
			int grade, DaoFactoryType factoryType) {
		ApplicationDao applicationDao = DaoFactory.getFactory(factoryType)
				.createApplicationDao();

		return applicationDao.updateExam(application.getId(), subject.getId(),
				grade);
	}
}