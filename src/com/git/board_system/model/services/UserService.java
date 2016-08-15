package com.git.board_system.model.services;

import java.util.List;

import com.git.board_system.model.dao.DaoFactory;
import com.git.board_system.model.dao.DaoFactoryType;
import com.git.board_system.model.dao.GenericDao;
import com.git.board_system.model.entities.User;

/**
 * Provides methods for working with User objects and database for Controller
 * 
 * @see User
 * @see GenericDao
 */
public class UserService {

	private static final UserService instance = new UserService();

	private UserService() {
	}

	public static UserService getInstance() {
		return instance;
	}

	/**
	 * Adds new user to database
	 * 
	 * @param user
	 *            User to add
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return Index of user in database, negative number if it wasn't inserted
	 */
	public int addUser(User user, DaoFactoryType factoryType) {
		GenericDao<User> userDao = DaoFactory.getFactory(factoryType)
				.createUserDao();

		return userDao.insert(user);
	}

	/**
	 * Edits user in database
	 * 
	 * @param user
	 *            User to edit
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return boolean value, indicating if user was edited
	 */
	public boolean editUser(User user, DaoFactoryType factoryType) {
		GenericDao<User> userDao = DaoFactory.getFactory(factoryType)
				.createUserDao();

		return userDao.update(user);
	}

	/**
	 * Deletes user from database
	 * 
	 * @param user
	 *            User to delete
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return boolean value, indicating if user was deleted
	 */
	public boolean deleteUser(User user, DaoFactoryType factoryType) {
		GenericDao<User> userDao = DaoFactory.getFactory(factoryType)
				.createUserDao();

		return userDao.delete(user.getId());
	}

	/**
	 * Returns all users from database
	 * 
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return List of all users
	 */
	public List<User> getAll(DaoFactoryType factoryType) {
		GenericDao<User> userDao = DaoFactory.getFactory(factoryType)
				.createUserDao();

		return userDao.getAll(-1, -1);
	}

	/**
	 * Finds user with given index in database
	 * 
	 * @param id
	 *            Index of user to find
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return User with given index, null if nothing was found
	 */
	public User find(int id, DaoFactoryType factoryType) {
		GenericDao<User> userDao = DaoFactory.getFactory(factoryType)
				.createUserDao();

		return userDao.find(id);
	}

	/**
	 * Finds user with given login in database
	 * 
	 * @param login
	 *            Index of user to find
	 * @param factoryType
	 *            Type of database to create DAO factory
	 * @return User with given login, null if nothing was found
	 */
	public User findByLogin(String login, DaoFactoryType factoryType) {
		GenericDao<User> userDao = DaoFactory.getFactory(factoryType)
				.createUserDao();

		return userDao.findByParameter(login);
	}
}