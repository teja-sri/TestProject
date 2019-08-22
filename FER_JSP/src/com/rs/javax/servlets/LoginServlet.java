package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;
import com.rs.fer.util.HTMLUtil;

public class LoginServlet extends HttpServlet {

	Service service = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		service = new ServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("userName");
		String password = request.getParameter("passWord");
		
		PrintWriter out = response.getWriter();
		int userId =  service.login(username, password);
		
		if(userId > 0) {
			
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("userId", userId);
			
			HTMLUtil.displayHeaderandLeftframe(request, response, out, username);
			
			out.println("Welcome  "+username);
			
			HTMLUtil.displayFooter(request, response);
			
		}else {
			out.println("invalid username/password.. try again..");
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.html");
			requestDispatcher.include(request, response);
		}

	}
	
	@Override
	public void destroy() {
		service = null;
	}

}
