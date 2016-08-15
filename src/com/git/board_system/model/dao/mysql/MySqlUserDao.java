package com.git.board_system.model.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.git.board_system.model.entities.User;
import com.git.board_system.model.entities.User.Role;

/**
 * Implementation of Generic DAO for User objects and MySql database
 * 
 * @see MySqlAbstractDao
 */
public class MySqlUserDao extends MySqlAbstractDao<User> {

	/* Query strings */
	
	private static final String CREATE = "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY"
										+ " AUTO_INCREMENT, login VARCHAR(20) NOT NULL UNIQUE,"
										+ " password VARCHAR(32) NOT NULL, name VARCHAR(25)"
										+ " NOT NULL, surname VARCHAR(25) NOT NULL, role"
										+ " ENUM('ENROLLEE', 'ADMIN') NOT NULL)";

	private static final String INSERT = "INSERT INTO user (login, password, name, surname, role)"
										+ " VALUES(?,?,?,?,?)";

	private static final String UPDATE = "UPDATE user SET login=?, password=?, name=?, surname=?,"
										+ " role=? WHERE id=?";

	private static final String DELETE = "DELETE FROM user WHERE id=?";

	private static final String GET_ALL = "SELECT id, login, password, name, surname, role"
										+ " FROM user";
	
	private static final String FIND = GET_ALL + " WHERE id=?";
	
	private static final String FIND_BY_PARAM = GET_ALL + " WHERE login=?";

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
			User user) throws SQLException {
		preparedStatement.setString(1, user.getLogin());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getName());
		preparedStatement.setString(4, user.getSurname());
		preparedStatement.setString(5, user.getRole().toString());
	}

	@Override
	public void prepareStatementForUpdate(PreparedStatement preparedStatement,
			User user) throws SQLException {
		prepareStatementForInsert(preparedStatement, user);
		preparedStatement.setInt(6, user.getId());
	}

	@Override
	public User parseResultSet(ResultSet resultSet) throws SQLException {
		return new User(resultSet.getInt("id"), resultSet.getString("login"),
				resultSet.getString("password"), resultSet.getString("name"),
				resultSet.getString("surname"), Role.valueOf(resultSet
						.getString("role")));
	}
}
