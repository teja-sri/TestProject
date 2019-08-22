package com.fer.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;

import com.fer.bean.Expense;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class ExpenseReportAction extends DispatchAction{
	public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		return mapping.findForward("expenseReportDropDown");

	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaValidatorForm dynaValidatorForm = (DynaValidatorForm) form;

		HttpSession session = request.getSession();
		
		int numberOfRecords = 0;
		int numberOfRecordsPerPage = 100;
		int pageIndex = (request.getParameter("pageIndex") == null) ? 1 : Integer.parseInt(request.getParameter("pageIndex"));
		
		FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();
		ArrayList<Expense> expenses = familyExpenseReportService.expenseReport(dynaValidatorForm.get("expenseType").toString(),
				dynaValidatorForm.get("expenseName").toString(), dynaValidatorForm.get("byWhom").toString(),
				dynaValidatorForm.get("fromDate").toString(), dynaValidatorForm.get("toDate").toString(),
				numberOfRecords, numberOfRecordsPerPage, pageIndex);
		
		session.setAttribute("expenses", expenses);

		return mapping.findForward("expenseReportList");

	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaValidatorForm dynaValidatorForm = (DynaValidatorForm) form;
		FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();

		Expense expense = familyExpenseReportService.generateExpenses(request.getParameter("Id"));

		HttpSession session = request.getSession();
		session.setAttribute("expense", expense);

		return mapping.findForward("updateExpenseReport");

	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaValidatorForm dynaValidatorForm = (DynaValidatorForm) form;

		FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();
		Expense expense = familyExpenseReportService.generateExpenses(dynaValidatorForm.get("expenseType").toString());

		HttpSession session = request.getSession();
		session.setAttribute("expense", expense);

	    int id= Integer.parseInt(request.getParameter("Id"));
	    expense.setId(id);
		expense.setType(dynaValidatorForm.get("expenseType").toString());
		expense.setDate(dynaValidatorForm.get("date").toString());
		expense.setPrice(Float.parseFloat(dynaValidatorForm.get("price").toString()));
		expense.setNumberOfItems(Integer.parseInt(dynaValidatorForm.get("numOfItems").toString()));
		expense.setTotal(Float.parseFloat(dynaValidatorForm.get("total").toString()));
		expense.setByWhom(dynaValidatorForm.get("byWhom").toString());

		boolean editFlag = familyExpenseReportService.editExpense(expense);

		if (editFlag) {

			return mapping.findForward("editExpenseUpdate");

		} else {
			return mapping.findForward("editExpenseFailure");
		}
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();
		DynaValidatorForm dynaValidatorForm = (DynaValidatorForm) form;

		Expense expense = new Expense();
		int id = Integer.parseInt(request.getParameter("Id"));
		expense.setId(id);
		boolean deleteExpenseFlag = familyExpenseReportService.deleteExpense(expense);

		if (deleteExpenseFlag) {

			return mapping.findForward("deleteExpenseStatus");

		}else{
		   
			return mapping.findForward("deleteExpensefailure");
	    }

	}
}
