package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;
import com.rs.fer.util.HTMLUtil;

public class DeleteExpenseServlet extends HttpServlet {

	Service service = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new ServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		HttpSession  session = request.getSession();
		
		boolean isIdExisted = service.deleteExpense(Integer.parseInt(request.getParameter("id")));
			
		//boolean isIdExisted = service.deleteExpense(2);

		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());

		out.println(isIdExisted ? "Record deleted successfully.." : "no records found with given id");

		HTMLUtil.displayFooter(request, response);

	}

	@Override
	public void destroy() {
		service = null;
	}

}
