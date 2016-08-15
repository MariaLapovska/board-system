package com.git.board_system.controller.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.model.entities.User;
import com.git.board_system.model.services.UserService;
import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;

/**
 * Command to delete user profile. It deletes user, sets session attribute user
 * to null and redirects to home page.
 */
public class DeleteProfileCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(Constants.USER);

		if (user != null) {
			if (UserService.getInstance().deleteUser(user, factoryType)) {
				logger.debug("delete user: " + user);
			}
		}

		request.getSession().setAttribute(Constants.USER, null);

		try {
			response.sendRedirect(request.getContextPath() + Links.HOME);
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}

		return "";
	}
}