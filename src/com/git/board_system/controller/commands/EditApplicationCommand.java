package com.git.board_system.controller.commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.model.entities.Application;
import com.git.board_system.model.entities.Subject;
import com.git.board_system.model.entities.User;
import com.git.board_system.model.services.ApplicationService;
import com.git.board_system.model.services.FacultyService;
import com.git.board_system.model.services.SubjectService;
import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;

/**
 * Command to add/edit application. It checks input and if it's incorrect
 * notifies user, otherwise - adds/edits application (depending on forwarded
 * parameter action and redirects back to profile page.
 */
public class EditApplicationCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String facultyId = request.getParameter(Constants.FACULTY);
		String certificateNumber = request
				.getParameter(Constants.CERTIFICATE_NUMBER);
		String certificateGrade = request
				.getParameter(Constants.CERTIFICATE_GRADE);
		String[] subjects = request.getParameterValues(Constants.SUBJECTS);
		String[] grades = request.getParameterValues(Constants.GRADES);
		String action = request.getParameter(Constants.ACTION);
		String goTo = Links.APPLICATION + action + Constants.MESSAGE_PARAM;

		FacultyService facultyService = FacultyService.getInstance();

		if (facultyId == null || facultyId.isEmpty()
				|| certificateNumber == null || certificateNumber.isEmpty()
				|| certificateGrade == null || certificateGrade.isEmpty()
				|| subjects == null || Arrays.asList(subjects).contains("")
				|| grades == null || Arrays.asList(grades).contains("")) { // fields
																			// are
																			// empty
			goTo += Constants.FILL_ALL;
		} else if (!certificateNumber.matches(Application.CERTIFICATE_PATTERN)
				|| !certificateGrade.matches(Application.GRADE_PATTERN)
				|| !Arrays.asList(subjects).stream()
						.allMatch(new HashSet<>()::add)
				|| !containsSubjects(subjects, facultyId, facultyService)
				|| !Arrays
						.asList(grades)
						.stream()
						.allMatch(
								grade -> grade
										.matches(Application.GRADE_PATTERN))) { // wrong
																				// format
			goTo += Constants.WRONG_INPUT;
		} else {
			ApplicationService applicationService = ApplicationService
					.getInstance();
			Application application = applicationService.findByCertificate(
					certificateNumber, FACTORY_TYPE);
			User user = (User) request.getSession()
					.getAttribute(Constants.USER);

			if ((application == null)
					|| (application != null && action.equals(Constants.EDIT) && application
							.getUser().getId() == user.getId())) {
				application = new Application();
				application.setUser(user);
				application.setFaculty(facultyService.find(
						Integer.parseInt(facultyId), FACTORY_TYPE));
				application.setCertificateNumber(certificateNumber);
				application.setCertificateGrade(Integer
						.parseInt(certificateGrade));

				if (action.equals(Constants.ADD)) {
					application.setId(applicationService.addApplication(
							application, FACTORY_TYPE));
				} else {
					application.setId(applicationService.findByUser(user,
							FACTORY_TYPE).getId());
					applicationService.editApplication(application,
							FACTORY_TYPE);
				}

				SubjectService subjectService = SubjectService.getInstance();
				Subject subject;
				int size = subjects.length;

				if (action.equals(Constants.EDIT)) {
					applicationService.deleteExams(application, FACTORY_TYPE);
				}

				for (int i = 0; i < size; i++) { // add exams
					subject = subjectService.find(
							Integer.parseInt(subjects[i]), FACTORY_TYPE);
					int grade = Integer.parseInt(grades[i]);

					application.addExam(subject, grade);

					applicationService.insertExam(application, subject, grade,
							FACTORY_TYPE);
				}

				LOGGER.debug(action + " application: " + application);

				goTo = Links.PROFILE_PAGE + Constants.MESSAGE_PARAM
						+ Constants.CHANGES_SUCCESS;

			} else { // certificate already exists
				goTo += Constants.CERTIFICATE_TAKEN;
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

	private boolean containsSubjects(String[] subjects, String facultyId,
			FacultyService facultyService) {

		List<Subject> facultySubjects = facultyService.getSubjects(
				Integer.parseInt(facultyId), FACTORY_TYPE);

		for (String subjectString : subjects) {
			int subjectId = Integer.parseInt(subjectString);

			if (!facultySubjects.stream().anyMatch(
					subject -> subject.getId() == subjectId)) {
				return false;
			}
		}

		return true;
	}
}