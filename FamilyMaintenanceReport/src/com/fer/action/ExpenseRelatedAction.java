package com.fer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;

import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class ExpenseRelatedAction extends DispatchAction	{
		
	FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();

	public ActionForward displayExpenseGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("displayExpenseGroup");
	}
	
	public ActionForward saveExpenseGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		DynaValidatorForm dynaValidatorForm = (DynaValidatorForm) form;
		HttpSession session = request.getSession();
		
		boolean saveExpenseGroupFlag = familyExpenseReportService.saveExpenseGroup(dynaValidatorForm.getString("expenseGroup"));
		session.setAttribute("SAVE_EXPENSE_GROUP_STATUS", 
			(saveExpenseGroupFlag) ? "Expense group added successfully" : "Expense group add failed");
		
		return mapping.findForward("saveExpenseGroupStatus");
	}
	
	public ActionForward displayExpenseName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("displayExpenseName");
	}
	
	public ActionForward saveExpenseName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		DynaValidatorForm dynaValidatorForm = (DynaValidatorForm) form;
		HttpSession session = request.getSession();
		
		boolean saveExpenseGroupFlag = familyExpenseReportService.saveExpenseName(dynaValidatorForm.getString("expenseName"));
		session.setAttribute("SAVE_EXPENSE_NAME_STATUS", 
			(saveExpenseGroupFlag) ? "Expense name added successfully" : "Expense name add failed");
		
		return mapping.findForward("saveExpenseNameStatus");
	}
}
