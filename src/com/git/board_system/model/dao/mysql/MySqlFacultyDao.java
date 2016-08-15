package com.git.board_system.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.LinkedList;

import com.git.board_system.model.dao.FacultyDao;
import com.git.board_system.model.entities.Faculty;
import com.git.board_system.model.entities.Subject;

/**
 * Implementation of Faculty DAO for MySql database
 * 
 * @see FacultyDao
 * @see MySqlAbstractDao
 */
public class MySqlFacultyDao extends MySqlAbstractDao<Faculty> implements
		FacultyDao {

	/* Query strings */
	
	private static final String CREATE = "CREATE TABLE IF NOT EXISTS faculty (id INT PRIMARY KEY"
										+ " AUTO_INCREMENT, name VARCHAR(50) NOT NULL UNIQUE,"
										+ " plan INT(4) NOT NULL, is_available BOOLEAN NOT NULL)";

	private static final String INSERT = "INSERT INTO faculty (name, plan, is_available)"
										+ " VALUES(?,?,?)";

	private static final String UPDATE = "UPDATE faculty SET name=?, plan=?, is_available=?"
										+ " WHERE id=?";

	private static final String DELETE = "DELETE FROM faculty WHERE id=?";

	private static final String GET_ALL = "SELECT id, name, plan, is_available FROM faculty";
	
	private static final String FIND = GET_ALL + " WHERE id=?";
	
	private static final String FIND_BY_PARAM = GET_ALL + " WHERE name=?";

	private static final String GET_SUBJECTS = "SELECT s.id, s.name FROM subject s INNER JOIN"
										+ " faculty_subject fs ON s.id = fs.subject_id AND"
										+ " fs.faculty_id=?";

	private static final String INSERT_SUBJECT = "INSERT INTO faculty_subject"
										+ " (faculty_id, subject_id) VALUES(?,?)";

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
			Faculty faculty) throws SQLException {
		preparedStatement.setString(1, faculty.getName());
		preparedStatement.setInt(2, faculty.getPlan());
		preparedStatement.setBoolean(3, faculty.isAvailable());
	}

	@Override
	public void prepareStatementForUpdate(PreparedStatement preparedStatement,
			Faculty faculty) throws SQLException {
		prepareStatementForInsert(preparedStatement, faculty);
		preparedStatement.setInt(4, faculty.getId());
	}

	@Override
	public Faculty parseResultSet(ResultSet resultSet) throws SQLException {
		return new Faculty(resultSet.getInt("id"), resultSet.getString("name"),
				resultSet.getInt("plan"), resultSet.getBoolean("is_available"));
	}

	@Override
	public List<Subject> getSubjects(int id) {
		List<Subject> subjects = new LinkedList<>();

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_SUBJECTS)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				subjects.add(new MySqlSubjectDao().parseResultSet(resultSet));
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return subjects;
	}

	@Override
	public int insertSubject(int facultyId, int subjectId) {
		int index = -1;
		
		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_SUBJECT, 
								PreparedStatement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, facultyId);
			preparedStatement.setInt(2, subjectId);

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
}