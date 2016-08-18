package com.git.board_system.controller.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;
import com.git.board_system.model.entities.User;
import com.git.board_system.model.services.UserService;

/**
 * Command to login to profile. It checks input and if it's incorrect notifies
 * user, otherwise - sets user as session attribute and redirects to profile
 * page.
 */
public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String login = request.getParameter(Constants.LOGIN);
		String password = request.getParameter(Constants.PASSWORD);
		String goTo = Links.LOGIN_PAGE + Constants.MESSAGE_PARAM;

		if (login == null || login.isEmpty() || password == null
				|| password.isEmpty()) { // fields are empty
			goTo += Constants.FILL_ALL; // go to log in page again
		} else {
			User user = UserService.getInstance().findByLogin(login,
					FACTORY_TYPE);

			if (user == null || !user.getPassword().equals(password)) { // user
																		// doesn't
																		// exist
																		// /
																		// password
																		// incorrect
				goTo += Constants.WRONG_INPUT;
			} else { // go to profile page
				goTo = Links.PROFILE_PAGE;
				request.getSession().setAttribute(Constants.USER, user);
			}
		}

		try {
			response.sendRedirect(request.getContextPath() + goTo);
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

		return "";
	}
}