package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.util.HTMLUtil;

public class DisplayExpenseReportServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();

		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());

		request.getRequestDispatcher("ExpenseReport.html").include(request, response);

		HTMLUtil.displayFooter(request, response);
	}

}
