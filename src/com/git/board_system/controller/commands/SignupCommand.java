package com.git.board_system.controller.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;
import com.git.board_system.model.entities.User;
import com.git.board_system.model.entities.User.Role;
import com.git.board_system.model.services.UserService;

/**
 * Command to signup to system. It checks input and if it's incorrect notifies
 * user, otherwise - adds new user and sets it as session attribute and
 * redirects to profile page.
 */
public class SignupCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String login = request.getParameter(Constants.LOGIN);
		String password = request.getParameter(Constants.PASSWORD);
		String repPassword = request.getParameter(Constants.REPEAT_PASSWORD);
		String name = request.getParameter(Constants.NAME);
		String surname = request.getParameter(Constants.SURNAME);
		String goTo = Links.SIGNUP_PAGE + Constants.MESSAGE_PARAM;

		if (login == null || login.isEmpty() || password == null
				|| password.isEmpty() || repPassword == null
				|| repPassword.isEmpty() || name == null || name.isEmpty()
				|| surname == null || surname.isEmpty()) { // fields are empty
			goTo += Constants.FILL_ALL; // go to signup page again
		} else if (!login.matches(User.LOGIN_PATTERN)
				|| !password.matches(User.LOGIN_PATTERN)
				|| !repPassword.equals(password)
				|| !name.matches(User.NAME_PATTERN)
				|| !surname.matches(User.NAME_PATTERN)) { // wrong format
			goTo += Constants.WRONG_INPUT; // go to signup page again
		} else {
			UserService userService = UserService.getInstance();
			User user = userService.findByLogin(login, FACTORY_TYPE);

			if (user == null) { // such user doesn't exist
				user = new User();

				user.setLogin(login);
				user.setPassword(password);
				user.setName(name);
				user.setSurname(surname);
				user.setRole(Role.ENROLLEE);

				user.setId(userService.addUser(user, FACTORY_TYPE));

				LOGGER.debug("add user: " + user);

				goTo = Links.PROFILE_PAGE;

				request.getSession().setAttribute(Constants.USER, user);

			} else { // login is taken
				goTo += Constants.LOGIN_TAKEN;
			}
		}

		try {
			response.sendRedirect(request.getContextPath() + goTo); // go to
																	// profile
																	// page
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

		return "";
	}
}
