package com.git.board_system.controller.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.model.entities.Application;
import com.git.board_system.model.entities.User;
import com.git.board_system.model.services.ApplicationService;
import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;

/**
 * Command to delete application. It deletes application and redirects back to
 * profile page.
 */
public class DeleteApplicationCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(Constants.USER);
		ApplicationService applicationService = ApplicationService
				.getInstance();
		Application application = applicationService.findByUser(user,
				FACTORY_TYPE);
		String goTo = Links.PROFILE_PAGE + Constants.MESSAGE_PARAM;

		if (application != null) {
			if (applicationService.deleteApplication(application, FACTORY_TYPE)) { // changes
																					// were
																					// made
				LOGGER.debug("delete application: " + application);
				goTo += Constants.CHANGES_SUCCESS;
			} else { // changes weren't made
				goTo += Constants.CHANGES_ERROR;
			}
		}

		try {
			response.sendRedirect(request.getContextPath() + goTo); // go to
																	// profile
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

		return "";
	}
}