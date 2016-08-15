package com.git.board_system.controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.git.board_system.controller.Constants;
import com.git.board_system.controller.Links;

/**
 * Servlet Filter implementation class SessionCheckFilter
 */
@WebFilter(filterName = "SessionCheckFilter", urlPatterns = "/board-system/*")
public class SessionCheckFilter implements Filter {

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fc) throws ServletException {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession(false);

		String URI = request.getRequestURI();
		boolean loggedIn = session != null
				&& session.getAttribute(Constants.USER) != null;

		if (loggedIn) { // user is logged in
			if (URI.contains("auth")) { // user tries to sign up or log in
				response.sendRedirect(request.getContextPath() + Links.HOME);
			} else {
				fc.doFilter(request, response);
			}
		} else { // user isn't logged in (guest)
			if (URI.contains("auth")) {
				fc.doFilter(request, response);
			} else { // guest tries to open or use profile
				response.sendRedirect(request.getContextPath()
						+ Links.LOGIN_PAGE + Constants.MESSAGE_PARAM
						+ Constants.UNAUTHORIZED);
			}
		}
	}
	
	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}
}