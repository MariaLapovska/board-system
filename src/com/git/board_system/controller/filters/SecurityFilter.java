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

import com.git.board_system.controller.Links;

/**
 * Servlet Filter implementation class SecurityFilter. If user tries to access
 * JSP pages, filter redirects him to home page
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = "/jsp/*")
public class SecurityFilter implements Filter {

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		response.sendRedirect(request.getContextPath() + Links.HOME);
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}
}