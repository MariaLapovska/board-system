package com.git.board_system.model.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.git.board_system.model.entities.Subject;

/**
 * Implementation of Generic DAO for Subject objects and MySql database
 * 
 * @see MySqlAbstractDao
 */
public class MySqlSubjectDao extends MySqlAbstractDao<Subject> {

	/* Query strings */
	
	private static final String CREATE = "CREATE TABLE IF NOT EXISTS subject (id INT PRIMARY KEY"
										+ " AUTO_INCREMENT, name VARCHAR(50) NOT NULL UNIQUE)";

	private static final String INSERT = "INSERT INTO subject (name) VALUES(?)";

	private static final String UPDATE = "UPDATE subject SET name=?, WHERE id=?";

	private static final String DELETE = "DELETE FROM subject WHERE id=?";
	
	private static final String GET_ALL = "SELECT id, name FROM subject";
	
	private static final String FIND = GET_ALL + " WHERE id=?";
	
	private static final String FIND_BY_PARAM = GET_ALL + " WHERE name=?";

	@Override
	public String getCreateQuery() {
		return CREATE;
	}

	@Override
	public String getInsertQuery() {
		return INSERT;
	}

	@Override
	public String getUpdateQuery() {
		return UPDATE;
	}

	@Override
	public String getDeleteQuery() {
		return DELETE;
	}

	@Override
	public String getFindQuery() {
		return FIND;
	}
	
	@Override
	public String getFindByParameterQuery() {
		return FIND_BY_PARAM;
	}

	@Override
	public String getSelectAllQuery() {
		return GET_ALL;
	}

	@Override
	public void prepareStatementForInsert(PreparedStatement preparedStatement,
			Subject subject) throws SQLException {
		preparedStatement.setString(1, subject.getName());
	}

	@Override
	public void prepareStatementForUpdate(PreparedStatement preparedStatement,
			Subject subject) throws SQLException {
		prepareStatementForInsert(preparedStatement, subject);
		preparedStatement.setInt(2, subject.getId());
	}

	@Override
	public Subject parseResultSet(ResultSet resultSet) throws SQLException {
		return new Subject(resultSet.getInt("id"), resultSet.getString("name"));
	}
}
