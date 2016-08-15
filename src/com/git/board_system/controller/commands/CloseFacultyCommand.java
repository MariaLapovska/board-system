package com.git.board_system.controller.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;
import com.git.board_system.model.entities.Faculty;
import com.git.board_system.model.services.FacultyService;

/**
 * Command to close applications submission to the faculty.
 * If faculty wasn't chosen it notifies user, otherwise - closes faculty.
 * Finally it redirects user back to profile page.
 */
public class CloseFacultyCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String facultyString = request.getParameter(Constants.FACULTY);
		String goTo = Links.PROFILE_PAGE + Constants.MESSAGE_PARAM;
		
		if (facultyString.isEmpty() || facultyString == null) { // empty faculty
			goTo += Constants.FILL_ALL;
		} else {
			FacultyService facultyService = FacultyService.getInstance();
			int facultyId = Integer.parseInt(facultyString);
			Faculty faculty = facultyService.find(facultyId, factoryType);
			
			if (facultyService.closeFaculty(faculty, factoryType)) { // changes were made
				logger.debug("close faculty: " + faculty);
				goTo += Constants.CHANGES_SUCCESS;
			} else { // changes weren't made
				goTo += Constants.CHANGES_ERROR;
			}
		}
		
		try {
			response.sendRedirect(request.getContextPath() + goTo); // go to
																	// profile
																	// page
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return "";
	}
}