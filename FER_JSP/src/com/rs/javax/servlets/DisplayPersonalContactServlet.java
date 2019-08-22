package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.pojo.User;
import com.rs.fer.util.HTMLUtil;

public class DisplayPersonalContactServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("userObj");

		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));

		PrintWriter out = response.getWriter();

		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());

		out.println("<table border='1px' align='center' cellSpacing='0' cellPadding='2px'>");
		out.println("<tr>");
		out.println("	<td colspan='2' align='center'>Contact Info</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Mobile No</td>");
		out.println("	<td><input type ='text' name='mobileNo' value='" + user.getMobile() + "'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Email</td>");
		out.println("	<td><input type ='text' name='mail' value='" + user.getEmail() + "'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td colspan='2' align='center' >");
		out.println(
				"	<input type='submit' value='Next' onclick='javascript: submitForm(\"displayPersonalAddress\")'>");
		out.println("	</td>");
		out.println("</tr>	");
		out.println("</table>");

		HTMLUtil.displayFooter(request, response);
	}
}
