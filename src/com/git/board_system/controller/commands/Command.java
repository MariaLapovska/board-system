package com.git.board_system.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.git.board_system.model.dao.DaoFactoryType;

/**
 * Command interface for Controller
 */
public interface Command {

	/** Database type to use */
	DaoFactoryType factoryType = DaoFactoryType.MY_SQL;

	/** Logger to use for debugging and logging */
	Logger logger = Logger.getLogger(Command.class);

	/**
	 * Processes request and response received from Controller servlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return Link to the page to forward request or empty string, if inside
	 *         body of method sendRedirect was called
	 */
	String execute(HttpServletRequest request, HttpServletResponse response);
}