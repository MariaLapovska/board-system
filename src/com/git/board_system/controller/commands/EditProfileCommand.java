package com.git.board_system.controller.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.model.entities.User;
import com.git.board_system.model.services.UserService;
import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;

/**
 * Command to edit user profile. It checks input and if it's incorrect notifies
 * user, otherwise - edits user and sets it as session attribute and
 * redirects back to profile page.
 */
public class EditProfileCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String login = request.getParameter(Constants.LOGIN);
		String oldPassword = request.getParameter(Constants.OLD_PASSWORD);
		String newPassword = request.getParameter(Constants.NEW_PASSWORD);
		String repNewPassword = request
				.getParameter(Constants.REPEAT_NEW_PASSWORD);
		String name = request.getParameter(Constants.NAME);
		String surname = request.getParameter(Constants.SURNAME);
		String goTo = Links.EDIT_PROFILE_PAGE + Constants.MESSAGE_PARAM;
		User user = (User) request.getSession().getAttribute(Constants.USER);

		if (login == null || login.isEmpty() || oldPassword == null
				|| oldPassword.isEmpty() || newPassword == null
				|| newPassword.isEmpty() || repNewPassword == null
				|| repNewPassword.isEmpty() || name == null || name.isEmpty()
				|| surname == null || surname.isEmpty()) { // fields are empty
			goTo += Constants.FILL_ALL;
		} else if (!login.matches(User.LOGIN_PATTERN)
				|| !oldPassword.equals(user.getPassword())
				|| !newPassword.matches(User.LOGIN_PATTERN)
				|| !repNewPassword.equals(newPassword)
				|| !name.matches(User.NAME_PATTERN)
				|| !surname.matches(User.NAME_PATTERN)) { // wrong format
			goTo += Constants.WRONG_INPUT;
		} else {
			UserService userService = UserService.getInstance();

			user.setPassword(newPassword);
			user.setName(name);
			user.setSurname(surname);

			goTo = Links.PROFILE_PAGE + Constants.MESSAGE_PARAM;

			if (userService.editUser(user, factoryType)) { // changes were made
				request.getSession().setAttribute(Constants.USER, user);
				logger.debug("edit user: " + user);
				goTo += Constants.CHANGES_SUCCESS;
			} else { // changes weren't made
				goTo += Constants.CHANGES_ERROR;
			}

			try {
				response.sendRedirect(request.getContextPath() + goTo); // go to
																		// profile
																		// page
				goTo = "";
			} catch (IOException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}

		return goTo; // go to edit profile page again
	}
}