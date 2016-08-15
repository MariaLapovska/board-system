package com.git.board_system.model.dao.mysql;

import java.sql.Connection;
import javax.sql.DataSource;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import com.git.board_system.model.dao.DaoFactory;
import com.git.board_system.model.dao.ApplicationDao;
import com.git.board_system.model.dao.FacultyDao;
import com.git.board_system.model.entities.Subject;
import com.git.board_system.model.entities.User;

/**
 * Implementation of DAO factory for MySql database
 * 
 * @see DaoFactory
 */
public class MySqlDaoFactory extends DaoFactory {
	
	/** Data source name */
	private static final String DATA_SOURCE = "java:comp/env/jdbc/board_system";
	
	/** Data source to use for creating connection */
	private static DataSource dataSource;
	
	/** Logger to use for debugging and logging */
	private static Logger logger;

    public static Connection getConnection() {
    	Connection connection = null;
    	
    	try {
	        if (dataSource == null) {
	        	dataSource = (DataSource) new InitialContext().lookup(DATA_SOURCE);
	        }
	        
        	connection = dataSource.getConnection();
        } catch (Exception ex) {
			getLogger().error(ex.getMessage(), ex);
		}
        
        return connection;
    }
    
    public static Logger getLogger() {
    	if (logger == null) {
    		logger = Logger.getLogger(MySqlDaoFactory.class);
    	}
    	
    	return logger;
    }

	@Override
	public MySqlAbstractDao<Subject> createSubjectDao() {
		return new MySqlSubjectDao();
	}

	@Override
	public MySqlAbstractDao<User> createUserDao() {
		return new MySqlUserDao();
	}
	
	@Override
	public ApplicationDao createApplicationDao() {
		return new MySqlApplicationDao();
	}

	@Override
	public FacultyDao createFacultyDao() {
		return new MySqlFacultyDao();
	}
}