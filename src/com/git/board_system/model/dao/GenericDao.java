package com.git.board_system.model.dao;

import java.util.List;

/**
 * Provides generic interface with necessary methods for working with objects
 * and database.
 *
 * @param <E>
 *            Generic type E
 */
public interface GenericDao<E> {

	/**
	 * Creates table in database for storage of objects of E type
	 */
	void createTable();

	/**
	 * Inserts e object into table
	 * 
	 * @param e
	 *            Object of E type to insert
	 * @return Index of e object in table, negative value if object wasn't
	 *         inserted
	 */
	int insert(E e);

	/**
	 * Updates e object in table
	 * 
	 * @param e
	 *            Object of E type to update
	 * @return boolean value, indicating if update was made
	 */
	boolean update(E e);

	/**
	 * Deletes object with given index from table
	 * 
	 * @param id
	 *            Index of the object to delete
	 * @return boolean value, indicating if object was deleted
	 */
	boolean delete(int id);

	/**
	 * Finds object with given index in table
	 * 
	 * @param id
	 *            Index of the object to find
	 * @return Object of E type with given index, null if nothing was found
	 */
	E find(int id);

	/**
	 * Finds object by given parameter in table
	 * 
	 * @param parameter
	 *            Parameter string to search by
	 * @return Object of E type with given parameter, null if nothing was found
	 */
	E findByParameter(String parameter);

	/**
	 * Returns given quantity of objects from table starting from given index. If
	 * from and/or quantity values are negative, gets all records
	 * 
	 * @param from
	 *            Index from which to get objects
	 * @param quantity
	 *            Quantity of objects to get
	 * @return List with gotten objects
	 */
	List<E> getAll(int from, int quantity);
}