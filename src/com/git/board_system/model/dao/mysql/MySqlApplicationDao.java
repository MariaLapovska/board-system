package com.git.board_system.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.git.board_system.model.dao.ApplicationDao;
import com.git.board_system.model.entities.Application;
import com.git.board_system.model.entities.Faculty;
import com.git.board_system.model.entities.Subject;
import com.git.board_system.model.entities.User;
import com.git.board_system.model.entities.User.Role;

/**
 * Implementation of Application DAO for MySql database
 * 
 * @see ApplicationDao
 * @see MySqlAbstractDao
 */
public class MySqlApplicationDao extends MySqlAbstractDao<Application>
		implements ApplicationDao {

	/* Query strings */
	
	private static final String CREATE = "CREATE TABLE IF NOT EXISTS application (id INT PRIMARY KEY"
										+ " AUTO_INCREMENT, user_id INT NOT NULL UNIQUE, faculty_id"
										+ " INT NOT NULL, certificate_number VARCHAR(10) NOT NULL"
										+ " UNIQUE, certificate_grade INT(3) NOT NULL, FOREIGN KEY"
										+ " (user_id) REFERENCES user(id), FOREIGN KEY (faculty_id)"
										+ " REFERENCES faculty(id))";

	private static final String INSERT = "INSERT INTO application (user_id, faculty_id,"
										+ " certificate_number, certificate_grade) VALUES(?,?,?,?)";

	private static final String UPDATE = "UPDATE application SET user_id=?, faculty_id=?,"
										+ " certificate_number=?, certificate_grade=? WHERE id=?";

	private static final String DELETE = "DELETE FROM application WHERE id=?";

	private static final String GET_ALL = "SELECT a.id, a.user_id, a.faculty_id,"
										+ " a.certificate_number, a.certificate_grade, u.login,"
										+ " u.password, u.name, u.surname, u.role, f.name AS"
										+ " faculty_name, f.plan, f.is_available FROM application a"
										+ " INNER JOIN user u ON a.user_id = u.id INNER JOIN"
										+ " faculty f ON a.faculty_id = f.id";

	private static final String FIND = GET_ALL + " AND a.id=?";

	private static final String FIND_BY_PARAM = GET_ALL + " AND a.certificate_number=?";

	private static final String FIND_BY_USER = GET_ALL + " AND a.user_id=?";

	private static final String ORDER_BY = " INNER JOIN application_subject sa ON sa.application_id"
										+ " = a.id GROUP BY a.id ORDER BY (a.certificate_grade +"
										+ " SUM(sa.grade)) DESC";

	private static final String GET_EXAMS = "SELECT s.id, s.name, a.grade FROM application_subject a"
										+ " INNER JOIN subject s ON s.id = a.subject_id AND"
										+ " a.application_id=?";

	private static final String INSERT_EXAM = "INSERT INTO application_subject (application_id,"
										+ " subject_id, grade) VALUES(?,?,?)";
	
	private static final String UPDATE_EXAM = "UPDATE application_subject SET subject_id=?, grade=?"
										+ " WHERE application_id=?";

	private static final String COUNT_ALL = "SELECT COUNT(*) FROM application a";

	private static final String FILTER_FACULTY = " INNER JOIN faculty f ON a.faculty_id = f.id"
										+ " WHERE f.id=";
	
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
		return GET_ALL + ORDER_BY;
	}

	@Override
	public void prepareStatementForInsert(PreparedStatement preparedStatement,
			Application application) throws SQLException {
		preparedStatement.setInt(1, application.getUser().getId());
		preparedStatement.setInt(2, application.getFaculty().getId());
		preparedStatement.setString(3, application.getCertificateNumber());
		preparedStatement.setInt(4, application.getCertificateGrade());
	}

	@Override
	public void prepareStatementForUpdate(PreparedStatement preparedStatement,
			Application application) throws SQLException {
		prepareStatementForInsert(preparedStatement, application);
		preparedStatement.setInt(5, application.getId());
	}

	@Override
	public Application parseResultSet(ResultSet resultSet) throws SQLException {
		return new Application(resultSet.getInt("id"), new User(
				resultSet.getInt("user_id"), resultSet.getString("login"),
				resultSet.getString("password"), resultSet.getString("name"),
				resultSet.getString("surname"), Role.valueOf(resultSet
						.getString("role"))), new Faculty(
				resultSet.getInt("faculty_id"),
				resultSet.getString("faculty_name"), resultSet.getInt("plan"),
				resultSet.getBoolean("is_available")),
				resultSet.getString("certificate_number"),
				resultSet.getInt("certificate_grade"));
	}

	@Override
	public Application findByUser(int userId) {
		Application application = null;

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(FIND_BY_USER)) {

			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				application = parseResultSet(resultSet);
			}

		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return application;
	}

	@Override
	public List<Application> getByFaculty(int from, int quantity, int facultyId) {
		List<Application> applications = new LinkedList<>();
		Application application;
		String selectQuery = GET_ALL + " AND f.id=" + facultyId + ORDER_BY;

		if (from >= 0 && quantity > 0) {
			selectQuery += " LIMIT " + from + "," + quantity;
		}

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(selectQuery)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				application = parseResultSet(resultSet);
				applications.add(application);
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return applications;
	}

	@Override
	public int getApplicationsNumber(int facultyId) {
		int appplicationsNumber = -1;

		try (Connection connection = MySqlDaoFactory.getConnection();
				Statement statement = connection.createStatement()) {

			String selectQuery = COUNT_ALL;

			if (facultyId >= 0) {
				selectQuery += FILTER_FACULTY + facultyId;
			}

			ResultSet resultSet = statement.executeQuery(selectQuery);

			while (resultSet.next()) {
				appplicationsNumber = resultSet.getInt("COUNT(*)");
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return appplicationsNumber;
	}

	@Override
	public Map<Subject, Integer> getExams(int id) {
		Map<Subject, Integer> exams = new HashMap<>();

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_EXAMS)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				exams.put(new MySqlSubjectDao().parseResultSet(resultSet),
						resultSet.getInt("grade"));
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return exams;
	}

	@Override
	public int insertExam(int applicationId, int subjectId, int grade) {
		int index = -1;

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_EXAM,
								PreparedStatement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, applicationId);
			preparedStatement.setInt(2, subjectId);
			preparedStatement.setInt(3, grade);

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
	public boolean updateExam(int applicationId, int subjectId, int grade) {
		boolean result = false;

		try (Connection connection = MySqlDaoFactory.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(UPDATE_EXAM)) {

			preparedStatement.setInt(1, subjectId);
			preparedStatement.setInt(2, grade);
			preparedStatement.setInt(3, applicationId);

			if (preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		} catch (Exception ex) {
			MySqlDaoFactory.getLogger().error(ex.getMessage(), ex);
		}

		return result;
	}
}