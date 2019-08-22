package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.pojo.Expense;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;
import com.rs.fer.util.HTMLUtil;

public class DisplayDeleteExpenseServlet extends HttpServlet {

	Service service = new ServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		List<Expense> expenses = service.getExpenses(userId);

		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());

		if (expenses != null && !expenses.isEmpty()) {
			out.println("<table>");
			out.println("	<tr>");
			out.println("		<td align='center'>Expense Id</td>");
			out.println("		<td><select name='id'>");
			out.println("				<option selected='selected'>SelectId</option>");

			String expenseDesc = null;
			for (Expense expense : expenses) {
				expenseDesc = expense.getExpenseType() + ", " + expense.getDate() + ", " + expense.getTotal() + ", "
						+ expense.getBy_whom();

				out.println("				<option value='" + expense.getId() + "'>" + expenseDesc + "</option>");
			}

			out.println("		</select></td>");
			out.println("	</tr>");
			out.println("	<tr>");
			out.println("		<td colspan='2' align='center'><a");
			out.println("			href='javascript: submitForm(\"deleteExpense\")'>Delete</a></td>");
			out.println("	</tr>");
			out.println("</table>");
		}
		// request.getRequestDispatcher("DeleteExpense.html").include(request,
		// response);

		HTMLUtil.displayFooter(request, response);

	}

}
