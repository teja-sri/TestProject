package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.pojo.Expense;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;
import com.rs.fer.util.HTMLUtil;

public class EditExpenseServlet extends HttpServlet{
	
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
		
		Expense expense = (Expense)session.getAttribute("expenseObj");
		
		expense.setExpenseType(request.getParameter("expenseType"));
		expense.setDate(request.getParameter("date"));
		expense.setBy_whom(request.getParameter("byWhom"));
		expense.setNo_of_items(Integer.parseInt(request.getParameter("numberOfItems")));
		expense.setPrice(Float.parseFloat(request.getParameter("price")));
		expense.setTotal(Float.parseFloat(request.getParameter("total")));
	
		
		boolean isExpenseEdited = service.editExpense(expense);

		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());

		out.println(isExpenseEdited ? "Record updated successfully.." : "update failed..");

		HTMLUtil.displayFooter(request, response);

	}

	@Override
	public void destroy() {
		service = null;
	}


}
