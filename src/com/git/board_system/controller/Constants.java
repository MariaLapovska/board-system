package com.git.board_system.controller;

/**
 * Interface with constants for JSP (bundle keys, attributes, parameters) 
 */
public interface Constants {

	/* Bundle keys */
	
	String FILL_ALL = "fillAll";
	String WRONG_INPUT = "wrongInput";
	String UNAUTHORIZED = "unauthorized";
	String LOGIN_TAKEN = "loginTaken";
	String NAME_TAKEN = "nameTaken";
	String CERTIFICATE_TAKEN = "certificateTaken";
	String CHANGES_SUCCESS = "changesSuccess";
	String CHANGES_ERROR = "changesError";

	/* Parameters */
	
	String MESSAGE_PARAM = "?message=";
	String ACTION_PARAM = "?action=";
	
	/* Attributes */
	
	String SEARCH = "search";
	String SEARCH_MESSAGE = "searchMessage";
	String SELECTED_FACULTY = "selectedFaculty";

	String NO_OF_PAGES = "noOfPages";
	String CURRENT_PAGE = "currentPage";
	String PAGE = "page";

	String USER = "user";
	String FACULTY = "faculty";

	String FACULTIES_LIST = "facultiesList";
	String APPLICATIONS_LIST = "applicationsList";
	String SUBJECTS_LIST = "subjectsList";
	String EXAMS_LIST = "examsList";
	
	String LOGIN = "login";
	String PASSWORD = "password";
	String OLD_PASSWORD = "oldPassword";
	String NEW_PASSWORD = "newPassword";
	String REPEAT_NEW_PASSWORD = "repeatNewPassword";
	String NAME = "name";
	String SURNAME = "surname";
	String REPEAT_PASSWORD = "repeatPassword";

	String USER_APPLICATION = "userApplication";
	String APPLICATION_NO = "applicationNo";
	String TOTAL_APPLICATION_NO = "totalApplicationNo";

	String ACTION = "action";
	String ADD = "add";
	String EDIT = "edit";

	String PLAN = "plan";
	String SUBJECTS = "subjects";
	String GRADES = "grades";
	String CERTIFICATE_NUMBER = "certificateNumber";
	String CERTIFICATE_GRADE = "certificateGrade";
}