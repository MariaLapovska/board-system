package com.git.board_system.controller.commands;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;
import com.git.board_system.model.services.FacultyService;
import com.git.board_system.model.services.SubjectService;

/**
 * Command to pre-process edit application page. It sets as attributes list of
 * all available faculties and list of all subjects and forwards request to
 * application edit page. It also reads and forwards parameter action, which
 * indicates whether user wants to add or edit application.
 */
public class EditApplicationPageCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		FacultyService facultyService = FacultyService.getInstance();
		SubjectService subjectService = SubjectService.getInstance();
		request.setAttribute(
				Constants.FACULTIES_LIST,
				facultyService.getAll(factoryType).stream()
						.filter(faculty -> faculty.isAvailable())
						.collect(Collectors.toList())); // get all available
														// faculties
		request.setAttribute(Constants.SUBJECTS_LIST,
				subjectService.getAll(factoryType));

		String goTo = Links.EDIT_APPLICATION_PAGE + Constants.ACTION_PARAM;

		if (request.getRequestURI().endsWith(Constants.ADD)) {
			goTo += Constants.ADD; // action == add application
		} else {
			goTo += Constants.EDIT; // action == edit application
		}

		return goTo;
	}
}