package com.git.board_system.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.board_system.controller.commands.Command;
import com.git.board_system.model.entities.Application;
import com.git.board_system.model.entities.Faculty;
import com.git.board_system.model.services.ApplicationService;
import com.git.board_system.model.services.FacultyService;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet(name = "DownloadServlet", urlPatterns = "/board-system/faculty/download")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		FacultyService facultyService = FacultyService.getInstance();
		int facultyId = Integer.parseInt(request
				.getParameter(Constants.FACULTY));
		Faculty faculty = facultyService.find(facultyId, Command.FACTORY_TYPE);

		if (faculty != null) {

			ApplicationService applicationService = ApplicationService
					.getInstance();
			List<Application> result = applicationService.getByFaculty(0,
					faculty.getPlan(), facultyId, Command.FACTORY_TYPE);
			result.forEach(application -> application
					.setExams(applicationService.getExams(application,
							Command.FACTORY_TYPE)));

			StringBuilder sb = new StringBuilder();

			sb.append("List of Applications for " + faculty.getName() + "\r\n\r\n");
			sb.append(String.format(
					"%-3s | %-20s | %-20s | %-20s | %-20s | %-13s | %-5s", "ID",
					"Name", "Surname", "Certificate Number",
					"Certificate Grade", "Exam Grades", "Total")
					+ "\r\n\r\n");

			for (Application a : result) {
				sb.append(formatApplication(a) + "\r\n");
			}

			response.setContentType("text/plain");
			response.setHeader("Content-Disposition",
					"attachment; filename=register-" + faculty.getName()
							+ ".txt");

			try (ByteArrayInputStream in = new ByteArrayInputStream(sb
					.toString().getBytes("UTF-8"));
					OutputStream out = response.getOutputStream()) {
				byte[] buffer = new byte[4096];
				int length;

				while ((length = in.read(buffer)) > -1) {
					out.write(buffer, 0, length);
				}

				out.flush();
			} catch (Exception ex) {
				Command.LOGGER.error(ex.getMessage(), ex);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	private String formatApplication(Application a) {
		return String.format(
				"%-3d | %-20s | %-20s | %-20s | %-20d | %-3d, %-3d, %-3d | %-5d",
				a.getId(), a.getUser().getName(), a.getUser().getSurname(),
				a.getCertificateNumber(), a.getCertificateGrade(), a.getExams()
						.values().toArray()[0],
				a.getExams().values().toArray()[1], a.getExams().values()
						.toArray()[2], a.getSumGrade());
	}
}