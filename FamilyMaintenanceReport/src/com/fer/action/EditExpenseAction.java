package com.fer.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fer.bean.Expense;
import com.fer.bean.PersonalInfo;
import com.fer.form.ExpenseForm;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class EditExpenseAction extends DispatchAction {

	FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();

	public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();
		List<Expense> expenses = familyExpenseReportService.getExpenses();
		HttpSession session = request.getSession();
		session.setAttribute("expenses", expenses);

		return mapping.findForward("editExpenseDropDown");
	}

	public ActionForward get(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ExpenseForm expenseForm = (ExpenseForm) form;
		//int id = Integer.parseInt(request.getParameter("id"));
		String id = request.getParameter("Id");
		Expense expense = familyExpenseReportService.generateExpenses(id);
		//expense.setId(id);
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("expense", expense);

		return mapping.findForward("editExpense");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	

		FamilyExpenseReportServiceInterface familyExpenseReportServiceInterface = new FamilyExpenseReportServiceImpl();
		Expense expense = new Expense();
		ExpenseForm expenseForm = (ExpenseForm) form;
		HttpSession session = request.getSession();
		
		PersonalInfo personalInfo = ((PersonalInfo)request.getSession().getAttribute("personalInfo"));
		int userId = personalInfo.getUser().getId();
		
		int id = Integer.parseInt(session.getAttribute("id").toString());
		expense.setType(request.getParameter("type"));
		expense.setDate(request.getParameter("date"));
		expense.setPrice(Double.parseDouble(request.getParameter("price")));
		expense.setNumberOfItems(Integer.parseInt(request.getParameter("numberOfItems")));
		expense.setTotal(Double.parseDouble(request.getParameter("total")));
		expense.setId(id);
		expense.setName(request.getParameter("name"));
		expense.setByWhom(request.getParameter("byWhom"));
		expense.setDebitedFrom(request.getParameter("debitedFrom"));
		
		
		expense.setUserId(userId);
		
		boolean editFlag = familyExpenseReportServiceInterface.editExpense(expense);

		if (editFlag) {

			return mapping.findForward("editExpenseUpdate");

		} else {
			return mapping.findForward("editExpenseFailure");
		}
	}
}
