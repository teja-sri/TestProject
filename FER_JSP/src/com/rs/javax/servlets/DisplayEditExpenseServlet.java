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

public class DisplayEditExpenseServlet extends HttpServlet {
	
	Service service = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new ServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Expense expense = service.getExpense(Integer.parseInt(request.getParameter("id")));
		
		HttpSession session = request.getSession();
		
		session.setAttribute("expenseObj", expense);
		
		PrintWriter out = response.getWriter();

		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());
		
		if(expense != null) {
			
		out.println("<table border='1px' align='center' cellSpacing='0' cellPadding='2px'>");
		out.println("<tr>");
		out.println("	<td colspan='2' align='center'>Edit Expense</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Expense Type</td>");
		out.println("	<td><input type ='text' name='expenseType' value='"+expense.getExpenseType()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Date</td>");
		out.println("	<td><input type ='text' name='date' value='"+expense.getDate()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Price</td>");
		out.println("	<td><input type ='text' name='price' value='"+expense.getPrice()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Number of Items</td>");
		out.println("	<td><input type ='text' name='numberOfItems' value='"+expense.getNo_of_items()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td>Total</td>");
		out.println("	<td><input type ='text' name='total' value='"+expense.getTotal()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td> By Whom </td>");
		out.println("	<td><input type ='text' name='byWhom' value='"+expense.getBy_whom()+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("	<td colspan='2' align='center' >");
		out.println("	<a href='javascript: submitForm(\"editExpense\")' >Save</a>");
		out.println("	</td>");
		out.println("</tr>");	
		out.println("</table>");
		}
		
		HTMLUtil.displayFooter(request, response);

	}
	
	@Override
	public void destroy() {
		service = null;
	}
	

}
