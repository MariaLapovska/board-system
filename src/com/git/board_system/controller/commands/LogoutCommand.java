package com.git.board_system.controller.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;

/**
 * Command to logout. It sets session attribute user to null and redirects to
 * home page.
 */
public class LogoutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		request.getSession().setAttribute(Constants.USER, null);

		try {
			response.sendRedirect(request.getContextPath() + Links.HOME_PAGE); // go
																				// to
																				// home
																				// page
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}

		return "";
	}
}