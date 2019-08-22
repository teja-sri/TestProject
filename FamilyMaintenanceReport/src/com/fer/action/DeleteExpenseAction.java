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
import com.fer.form.ExpenseForm;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class DeleteExpenseAction extends DispatchAction {

	public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Expense expense = new Expense();
		ExpenseForm expenseForm =  (ExpenseForm) form;
		FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();
		List<Expense> expenses = familyExpenseReportService.getExpenses();
		HttpSession Session = request.getSession();
		Session.setAttribute("expenses", expenses);

		return mapping.findForward("deleteDropDown");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();
		ExpenseForm expenseForm = (ExpenseForm) form;
		Expense expense = new Expense();
		int id = Integer.parseInt(request.getParameter("id"));
		expense.setId(id);
		boolean deleteExpenseFlag = familyExpenseReportService.deleteExpense(expense);

		if (deleteExpenseFlag) {

			return mapping.findForward("deleteExpenseStatus");

		}else{
		   
			return mapping.findForward("deleteExpensefailure");
	    }

	}
}
