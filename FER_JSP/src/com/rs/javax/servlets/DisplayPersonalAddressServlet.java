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

public class DisplayPersonalAddressServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("userObj");
		
		user.setMobile(request.getParameter("mobileNo"));
		user.setEmail(request.getParameter("mail"));
		
		PrintWriter out = response.getWriter();

		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());
		
		out.println("<table border='1px' align='center' cellSpacing='0' cellPadding='2px'>");
		out.println("<tr>");
		out.println("	<td colspan='2' align='center'>Update Address Info</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Address Line1</td>");
		out.println("	<td><input type ='text' name='address1' value='"+user.getAddress().getLineOne()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Address Line2</td>");
		out.println("	<td><input type ='text' name='address2' value='"+user.getAddress().getLineTwo()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Street</td>");
		out.println("	<td><input type ='text' name='street' value='"+user.getAddress().getStreet()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>City</td>");
		out.println("	<td><input type ='text' name='city' value='"+user.getAddress().getCity()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>State</td>");
		out.println("	<td><input type ='text' name='state' value='"+user.getAddress().getState()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Pin Code</td>");
		out.println("	<td><input type ='text' name='zip' value='"+user.getAddress().getZip()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td colspan='2' align='center' >");
		out.println("	<input type='submit' value='Next' onclick='javascript: submitForm(\"displayPersonalReview\")'>");
		out.println("	</td>");
		out.println("</tr>	");
		out.println("</table>");

		HTMLUtil.displayFooter(request, response);
	}

}
