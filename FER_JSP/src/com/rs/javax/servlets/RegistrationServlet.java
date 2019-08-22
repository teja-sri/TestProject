package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rs.fer.pojo.User;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

//@webServlet("/Registration")
public class RegistrationServlet extends HttpServlet {

	Service service = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new ServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("passWord"));
		user.setEmail(request.getParameter("email"));
		user.setMobile(request.getParameter("mobile"));
		
		boolean isRegistered = service.register(user);
		String nextPath = "";
		
		PrintWriter out = response.getWriter();
		if(isRegistered) {
			out.println("Registered Successfully..please try with login..<br>");
			nextPath = "Login.html";
		}else {
			out.println("Registration failed..try again..<br>");
			nextPath = "Registration.html";
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPath);
		requestDispatcher.include(request,response);

	}

	@Override
	public void destroy() {
		service = null;
	}
}
