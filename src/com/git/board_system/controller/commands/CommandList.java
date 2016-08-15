package com.git.board_system.controller.commands;

/**
 * Enumeration of command names associated with Command objects
 */
public enum CommandList {
	HOME_PAGE(new HomePageCommand()), 
	LOGIN(new LoginCommand()), 
	SIGNUP(new SignupCommand()), 
	PROFILE(new ProfileCommand()), 
	LOGOUT(new LogoutCommand()), 
	EDIT_PROFILE(new EditProfileCommand()), 
	DELETE_PROFILE(new DeleteProfileCommand()), 
	EDIT_APPLICATION_PAGE(new EditApplicationPageCommand()),
	EDIT_APPLICATION(new EditApplicationCommand()), 
	DELETE_APPLICATION(new DeleteApplicationCommand()), 
	ADD_FACULTY_PAGE(new AddFacultyPageCommand()),
	ADD_FACULTY(new AddFacultyCommand()), 
	CLOSE_FACULTY(new CloseFacultyCommand());

	/** Command object associated with given enum element */
	private Command command;

	private CommandList(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

	/**
	 * Checks if CommandList contains given command
	 * 
	 * @param command
	 *            Command string to search
	 * @return boolean
	 */
	public static boolean contains(String command) {
		for (CommandList c : CommandList.values()) {
			if (c.toString().equals(command)) {
				return true;
			}
		}

		return false;
	}
}