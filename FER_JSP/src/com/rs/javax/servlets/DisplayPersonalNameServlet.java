package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.pojo.User;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;
import com.rs.fer.util.HTMLUtil;

public class DisplayPersonalNameServlet extends HttpServlet {

	Service service = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new ServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		User user = service.getPersonal(userId);

		session.setAttribute("userObj", user);

		PrintWriter out = response.getWriter();

		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());

		out.println("<table border='1px' align='center' cellSpacing='0' cellPadding='2px'>");
		out.println("<tr>");
		out.println("	<td colspan='2' align='center'>Update Personal Info</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>First Name</td>");
		out.println("	<td><input type ='text' name='firstName' value='" + user.getFirstName() + "'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Middle Name</td>");
		out.println("	<td><input type ='text' name='middleName' value='" + user.getMiddleName() + "'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>last Name</td>");
		out.println("	<td><input type ='text' name='lastName' value='" + user.getLastName() + "'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td colspan='2' align='center' >");
		out.println(
				"	<input type='submit' value='Next' onclick='javascript: submitForm(\"displayPersonalContact\")'>");
		out.println("	</td>");
		out.println("</tr>	");
		out.println("</table>");

		HTMLUtil.displayFooter(request, response);

	}

	@Override
	public void destroy() {
		service = null;
	}
}
