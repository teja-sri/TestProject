package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rs.fer.pojo.Expense;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;
import com.rs.fer.util.HTMLUtil;

public class ExpenseReportServlet extends HttpServlet {

	Service service = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new ServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String expenseType = request.getParameter("expenseType");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		List<Expense> list = service.expenseReport(2, expenseType, fromDate, toDate);

		PrintWriter out = response.getWriter();

		HTMLUtil.displayHeaderandLeftframe(request, response, out, "");
		if (list.size() > 0) {
			request.getRequestDispatcher("GetReport.html").forward(request, response);
			// response.sendRedirect("GetReport.html");0
		} else {
			out.println("no records found");
			request.getRequestDispatcher("ExpenseReport.html").include(request, response);
		}

		HTMLUtil.displayFooter(request, response);

	}

	@Override
	public void destroy() {
		service = null;
	}

}
