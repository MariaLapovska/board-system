package com.git.board_system.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.git.board_system.model.dao.GenericDao;

/**
 * Provides general implementation of GenericDao methods. Query strings,
 * prepareStatement methods and ResultSet parsers are defined by child classes.
 *
 * @param <E>
 *            Generic type E
 */
public abstract class MySqlAbstractDao<E> implements GenericDao<E> {

	public abstract String getCreateQuery();

	public abstract String getInsertQuery();

	public abstract String getUpdateQuery();

	public abstract String getDeleteQuery();

	public abstract String getFindQuery();

	public abstract String getFindByParameterQuery();

	public abstract String getSelectAllQuery();

	public abstract void prepareStatementForInsert(
			PreparedStatement preparedStatement, E e) throws SQLException;

	public abstract void prepareStatementForUpdate(
			PreparedStatement preparedStatement, E e) throws SQLException;

	public abstract E parseResultSet(ResultSet resultSet) throws SQLException;

	@Override
	public void createTable() {
		try (Connection connection = MySqlDaoFactory.getConnection();
				Statement statement = connection.createStatement()) {

			statement.execute(getCreateQuery());
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}
	}

	@Override
	public int insert(E e) {
		int index = -1;

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(getInsertQuery(),
								PreparedStatement.RETURN_GENERATED_KEYS)) {

			prepareStatementForInsert(preparedStatement, e);
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				index = rs.getInt(1);
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return index;
	}

	@Override
	public boolean update(E e) {
		boolean result = false;

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(getUpdateQuery())) {

			prepareStatementForUpdate(preparedStatement, e);

			if (preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(getDeleteQuery())) {

			preparedStatement.setInt(1, id);

			if (preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return result;
	}

	@Override
	public E find(int id) {
		E e = null;

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(getFindQuery())) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				e = parseResultSet(resultSet);
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return e;
	}

	@Override
	public E findByParameter(String parameter) {
		E e = null;

		if (parameter != null && !parameter.isEmpty()) {
			try (Connection connection = MySqlDaoFactory.getConnection();
					PreparedStatement preparedStatement = connection
							.prepareStatement(getFindByParameterQuery())) {

				preparedStatement.setString(1, parameter);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					e = parseResultSet(resultSet);
				}
			} catch (Exception ex) {
				MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
			}
		}

		return e;
	}

	@Override
	public List<E> getAll(int from, int quantity) {
		List<E> eList = new LinkedList<>();
		E e;

		String selectQuery = getSelectAllQuery();

		if (from >= 0 && quantity > 0) {
			selectQuery += " LIMIT " + from + "," + quantity;
		}

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(selectQuery)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				e = parseResultSet(resultSet);
				eList.add(e);
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return eList;
	}
}