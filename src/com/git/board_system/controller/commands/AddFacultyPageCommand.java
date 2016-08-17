package com.git.board_system.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;
import com.git.board_system.model.services.SubjectService;

/**
 * Command to pre-process add faculty page. It sets as attribute list of all
 * subjects and forwards request to JSP page.
 */
public class AddFacultyPageCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		SubjectService subjectService = SubjectService.getInstance();
		request.setAttribute(Constants.SUBJECTS_LIST,
				subjectService.getAll(FACTORY_TYPE));

		return Links.ADD_FACULTY_PAGE;
	}
}