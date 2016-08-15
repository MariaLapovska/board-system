package com.git.board_system.controller.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Handler for custom Error tag
 */
public class ErrorTag extends SimpleTagSupport {

	/** Code of the error */
	private String code;
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public void doTag() throws IOException, JspException {
		Writer out = getJspContext().getOut();

		if (code.equals("404")) {
			out.write("Error 404. Page not found. ");
		} else if (code.equals("500")) {
			out.write("Error 500. Internal server error. ");
		}
		
		getJspBody().invoke(out);
	}
}