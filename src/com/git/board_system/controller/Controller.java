package com.git.board_system.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.commands.Command;
import com.git.board_system.controller.commands.CommandList;

/**
 * Servlet implementation class Controller
 */
@MappingTable(table = {
		@Mapping(link = "/", handle = "HOME_PAGE"),
		@Mapping(link = "/index.html", handle = "HOME_PAGE"),
		@Mapping(link = "/board-system/auth/signup", handle = "/jsp/auth/signup.jsp"),
		@Mapping(link = "/board-system/auth/login", handle = "/jsp/auth/login.jsp"),
		@Mapping(link = "/controller/signup", handle = "SIGNUP"),
		@Mapping(link = "/controller/login", handle = "LOGIN"),
		@Mapping(link = "/controller/logout", handle = "LOGOUT"),

		@Mapping(link = "/board-system/profile", handle = "PROFILE"),
		@Mapping(link = "/board-system/profile/edit", handle = "/jsp/profile/editProfile.jsp"),
		@Mapping(link = "/board-system/profile/delete", handle = "/jsp/profile/deleteProfile.jsp"),
		@Mapping(link = "/controller/profile/edit", handle = "EDIT_PROFILE"),
		@Mapping(link = "/controller/profile/delete", handle = "DELETE_PROFILE"),

		@Mapping(link = "/board-system/application/add", handle = "EDIT_APPLICATION_PAGE"),
		@Mapping(link = "/board-system/application/edit", handle = "EDIT_APPLICATION_PAGE"),
		@Mapping(link = "/board-system/application/delete", handle = "/jsp/user/deleteApplication.jsp"),
		@Mapping(link = "/controller/application/edit", handle = "EDIT_APPLICATION"),
		@Mapping(link = "/controller/application/delete", handle = "DELETE_APPLICATION"),

		@Mapping(link = "/board-system/faculty/add", handle = "ADD_FACULTY_PAGE"),
		@Mapping(link = "/controller/faculty/add", handle = "ADD_FACULTY"),
		@Mapping(link = "/controller/faculty/close", handle = "CLOSE_FACULTY") })
@WebServlet(name = "Controller", urlPatterns = { 
		"/index.html",
		"/board-system/*", 
		"/controller/*" })
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** Mappings of links to handlers for them */
	private static final Map<String, String> mappingTable = MappingTableParser
			.parse(Controller.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String req = request.getRequestURI().substring(
				request.getContextPath().length());

		String commandName = mappingTable.get(req);

		if (commandName != null) { // handler for given link exists

			if (CommandList.contains(commandName)) { // handler for link is
														// command
				Command command = CommandList.valueOf(commandName).getCommand();
				commandName = command.execute(request, response);
			}

			if (!commandName.isEmpty()) { // page wasn't redirected
				RequestDispatcher rd = request
						.getRequestDispatcher(commandName);
				rd.forward(request, response);
			} else {
				return;
			}
		} else { // handler doesn't exist
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String req = request.getRequestURI().substring(
				request.getContextPath().length());

		String commandName = mappingTable.get(req);

		if (commandName != null) { // handler for given link exists

			if (CommandList.contains(commandName)) { // handler for link is
														// command
				Command command = CommandList.valueOf(commandName).getCommand();
				commandName = command.execute(request, response);
			}

			if (!commandName.isEmpty()) { // page wasn't redirected
				RequestDispatcher rd = request
						.getRequestDispatcher(commandName);
				rd.forward(request, response);
			} else {
				return;
			}
		} else { // handler doesn't exist
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}