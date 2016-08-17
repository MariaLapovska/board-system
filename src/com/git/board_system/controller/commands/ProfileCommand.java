package com.git.board_system.controller.commands;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;
import com.git.board_system.model.entities.Application;
import com.git.board_system.model.entities.Faculty;
import com.git.board_system.model.entities.User;
import com.git.board_system.model.entities.User.Role;
import com.git.board_system.model.services.ApplicationService;
import com.git.board_system.model.services.FacultyService;

/**
 * Command to pre-process profile page. Depending on user role it forwards
 * request to either admin or enrollee profile page. It sets available faculties
 * as attribute and for enrollee it sets his application.
 */
public class ProfileCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(Constants.USER);
		String goTo = null;

		FacultyService facultyService = FacultyService.getInstance();

		List<Faculty> result = facultyService.getAll(FACTORY_TYPE).stream()
				.filter(faculty -> faculty.isAvailable())
				.collect(Collectors.toList()); // get all available faculties

		if (user.getRole().equals(Role.ADMIN)) {
			request.setAttribute(Constants.FACULTIES_LIST, result);
			goTo = Links.ADMIN_PROFILE_PAGE; // go to admin profile
		} else {
			ApplicationService applicationService = ApplicationService
					.getInstance();

			Application application = applicationService.findByUser(user,
					FACTORY_TYPE);

			if (application != null) { // user has application
				request.setAttribute(Constants.USER_APPLICATION, application);
				request.setAttribute(Constants.APPLICATION_NO,
						applicationService.getApplicationIndex(application,
								FACTORY_TYPE) + 1); // get user
													// number
													// in
													// list
				request.setAttribute(Constants.TOTAL_APPLICATION_NO,
						applicationService.getApplicationsNumber(application
								.getFaculty().getId(), FACTORY_TYPE)); // get
																		// total
																		// number
																		// of
																		// applications
			}

			result.forEach(faculty -> faculty.setSubjects(facultyService
					.getSubjects(faculty.getId(), FACTORY_TYPE)));

			request.setAttribute(Constants.EXAMS_LIST, result);

			goTo = Links.USER_PROFILE_PAGE; // go to user profile
		}

		return goTo;
	}
}