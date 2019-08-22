package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.pojo.Address;
import com.rs.fer.pojo.User;
import com.rs.fer.util.HTMLUtil;

public class DisplayPersonalReviewServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("userObj");
		
		Address address = user.getAddress();
		
		address.setLineOne(request.getParameter("address1"));
		address.setLineTwo(request.getParameter("address2"));
		address.setState(request.getParameter("state"));
		address.setCity(request.getParameter("city"));
		address.setStreet(request.getParameter("street"));
		address.setZip(request.getParameter("zip"));
		
		PrintWriter out = response.getWriter();

		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());
		
		out.println("<table border='1px' align='center' cellSpacing='0' cellPadding='2px'>");
		out.println("<tr>");
		out.println("	<td colspan='2' align='center'>Review</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>First Name</td>");
		out.println("	<td><input type ='text' name='firstName' value='"+user.getFirstName()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Middle Name</td>");
		out.println("	<td><input type ='text' name='middleName' value='"+user.getMiddleName()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>last Name</td>");
		out.println("	<td><input type ='text' name='lastName' value='"+user.getLastName()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Mobile No</td>");
		out.println("	<td><input type ='text' name='mobileNo' value='"+user.getMobile()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Email</td>");
		out.println("	<td><input type ='text' name='mail' value='"+user.getEmail()+"'></td>");
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
		out.println("	<td><input type ='text' name='area' value='"+user.getAddress().getCity()+"'></td>");
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
		out.println("	<input type='submit' value='Next' onclick='javascript: submitForm(\"updatePersonalInfo\")'> ");
		out.println("	</td>");
		out.println("</tr>	");
		out.println("	</table>");

		HTMLUtil.displayFooter(request, response);
	}
	
	
}
