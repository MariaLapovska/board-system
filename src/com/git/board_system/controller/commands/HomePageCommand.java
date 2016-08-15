package com.git.board_system.controller.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;
import com.git.board_system.model.entities.Application;
import com.git.board_system.model.services.ApplicationService;
import com.git.board_system.model.services.FacultyService;

/**
 * Command to pre-process home page. It checks if faculty filter was selected or
 * there was search query and sets found applications as attribute, using
 * pagination.
 */
public class HomePageCommand implements Command {

	/** Number of records per page to output in table */
	private static final int RECORDS_PER_PAGE = 20;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		ApplicationService applicationService = ApplicationService
				.getInstance();
		FacultyService facultyService = FacultyService.getInstance();

		List<Application> result = new ArrayList<>();

		String search = request.getParameter(Constants.SEARCH);
		String faculty = request.getParameter(Constants.FACULTY);
		String page = request.getParameter(Constants.PAGE);

		int currentPage = 1;
		int noOfPages = 0; // total number of table pages
		int applicationsNumber = 0;

		if (search != null && !search.isEmpty()) { // search input
			if (search.matches(Application.CERTIFICATE_PATTERN)) { // input has
																	// correct
																	// format
				Application application = applicationService.findByCertificate(
						search, factoryType);

				if (application != null) {
					result.add(application);
				}
			} else { // certificate wrong format
				request.setAttribute(Constants.SEARCH_MESSAGE,
						Constants.WRONG_INPUT);
			}
		} else { // no search input
			if (page != null) { // current page is other than 1
				currentPage = Integer.parseInt(request
						.getParameter(Constants.PAGE));
			}

			int offset = (currentPage - 1) * RECORDS_PER_PAGE;

			if (faculty != null && !faculty.isEmpty()) { // faculty selected
				int facultyId = Integer.parseInt(faculty);

				result = applicationService.getByFaculty(offset,
						RECORDS_PER_PAGE, facultyId, factoryType);
				applicationsNumber = applicationService.getApplicationsNumber(
						facultyId, factoryType);
				request.setAttribute(Constants.SELECTED_FACULTY,
						facultyService.find(facultyId, factoryType));
			} else { // no faculty selected
				result = applicationService.getAll(offset, RECORDS_PER_PAGE,
						factoryType);
				applicationsNumber = applicationService
						.getApplicationsNumber(factoryType);
			}

			noOfPages = (int) Math.ceil(applicationsNumber * 1.0
					/ RECORDS_PER_PAGE);
		}

		result.forEach(application -> application.setExams(applicationService
				.getExams(application, factoryType)));

		request.setAttribute(Constants.APPLICATIONS_LIST, result);
		request.setAttribute(Constants.FACULTIES_LIST, FacultyService
				.getInstance().getAll(factoryType));
		request.setAttribute(Constants.NO_OF_PAGES, noOfPages);
		request.setAttribute(Constants.CURRENT_PAGE, currentPage);

		return Links.HOME_PAGE; // go to home page
	}
}