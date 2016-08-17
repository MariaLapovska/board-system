package com.git.board_system.controller;

/**
 * Interface with links to JSP pages and handlers
 */
public interface Links {
	
	String HOME = "/index.html";
	String HOME_PAGE = "/jsp/index.jsp";
	String LOGIN_PAGE = "/board-system/auth/login";
	String SIGNUP_PAGE = "/board-system/auth/signup";
	String PROFILE_PAGE = "/board-system/profile";
	String ADMIN_PROFILE_PAGE = "/jsp/admin/profile.jsp";
	String USER_PROFILE_PAGE = "/jsp/user/profile.jsp";
	String EDIT_PROFILE_PAGE = "/board-system/profile/edit";
	String ADD_FACULTY_PAGE = "/jsp/admin/addFaculty.jsp";
	String EDIT_APPLICATION_PAGE = "/jsp/user/editApplication.jsp";
	String ADD_FACULTY = "/board-system/faculty/add";
	String CLOSE_FACULTY = "/jsp/admin/closeFaculty.jsp";
	String APPLICATION = "/board-system/application/";
}