package com.git.board_system.controller.commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.model.entities.Faculty;
import com.git.board_system.model.entities.Subject;
import com.git.board_system.model.services.FacultyService;
import com.git.board_system.model.services.SubjectService;
import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;

/**
 * Command to add faculty. It checks input and if it's incorrect notifies user,
 * otherwise - adds new faculty and redirects back to profile page.
 */
public class AddFacultyCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter(Constants.NAME);
		String planString = request.getParameter(Constants.PLAN);
		String[] subjects = request.getParameterValues(Constants.SUBJECTS);
		String goTo = Links.ADD_FACULTY + Constants.MESSAGE_PARAM;

		if (name == null || name.isEmpty() || planString == null
				|| planString.isEmpty() || subjects == null
				|| Arrays.asList(subjects).contains("")) { // fields are empty
			goTo += Constants.FILL_ALL;
		} else if (!name.matches(Faculty.NAME_PATTERN)
				|| !planString.matches(Faculty.PLAN_PATTERN)
				|| !Arrays.asList(subjects).stream()
						.allMatch(new HashSet<>()::add)) { // wrong format
			goTo += Constants.WRONG_INPUT;
		} else {
			FacultyService facultyService = FacultyService.getInstance();
			Faculty faculty = facultyService.findByName(name, factoryType);

			if (faculty == null) {
				faculty = new Faculty();
				SubjectService subjectService = SubjectService.getInstance();
				Subject subject;

				faculty.setName(name);
				faculty.setPlan(Integer.parseInt(planString));
				faculty.setAvailable(true);

				faculty.setId(facultyService.addFaculty(faculty, factoryType));

				for (String subjectId : subjects) { // add subjects
					subject = subjectService.find(Integer.parseInt(subjectId),
							factoryType);
					faculty.addSubject(subject);

					facultyService.insertSubject(faculty, subject, factoryType);
				}

				logger.debug("add faculty: " + faculty);

				goTo = "";

				try {
					response.sendRedirect(request.getContextPath()
							+ Links.PROFILE_PAGE + Constants.MESSAGE_PARAM
							+ Constants.CHANGES_SUCCESS); // go to profile page
				} catch (IOException ex) {
					logger.error(ex.getMessage(), ex);
				}
			} else { // name is taken
				goTo += Constants.NAME_TAKEN;
			}
		}

		return goTo;
	}
}