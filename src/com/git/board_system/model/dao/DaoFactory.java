package com.git.board_system.model.dao;

import com.git.board_system.model.dao.mysql.MySqlDaoFactory;
import com.git.board_system.model.entities.Subject;
import com.git.board_system.model.entities.User;

/**
 * Provides abstract interface for working with different databases.
 */
public abstract class DaoFactory {

	public abstract GenericDao<Subject> createSubjectDao();

	public abstract GenericDao<User> createUserDao();

	public abstract ApplicationDao createApplicationDao();

	public abstract FacultyDao createFacultyDao();

	/**
	 * Returns DaoFactory of given type
	 * 
	 * @param factoryType
	 *            factory type to return
	 * @return DaoFactory of factoryType
	 */
	public static DaoFactory getFactory(DaoFactoryType factoryType) {
		if (factoryType == DaoFactoryType.MY_SQL) {
			return new MySqlDaoFactory();
		} else {
			return null;
		}
	}
}