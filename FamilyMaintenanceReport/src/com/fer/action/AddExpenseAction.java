package com.fer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fer.bean.Expense;
import com.fer.bean.PersonalInfo;
import com.fer.form.AddExpenseForm;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class AddExpenseAction extends DispatchAction{

	
	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addExpense");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FamilyExpenseReportServiceInterface familyExpenseReportServiceInterface = new FamilyExpenseReportServiceImpl();
		AddExpenseForm addExpenseForm = (AddExpenseForm) form;
		Expense expense = new Expense();
		
		PersonalInfo personalInfo = ((PersonalInfo)request.getSession().getAttribute("personalInfo"));
		int userId = personalInfo.getUser().getId();
		
		expense.setType(addExpenseForm.getType());
		expense.setName(addExpenseForm.getName());
		expense.setDebitedFrom(addExpenseForm.getDebitedFrom());
		expense.setDate(addExpenseForm.getDate());
		expense.setPrice(addExpenseForm.getPrice());
		expense.setNumberOfItems(addExpenseForm.getNumberOfItems());
		expense.setTotal(addExpenseForm.getTotal());
		expense.setByWhom(addExpenseForm.getByWhom());
		expense.setUserId(userId);
		
		boolean addExpenseFlag = familyExpenseReportServiceInterface.addExpense(expense);
		System.out.println("addExpenseFlag: " + addExpenseFlag);
		
		String forwardPage = "";
		if(addExpenseFlag){
			System.out.println("success");
			forwardPage = "success";
		}
		else{
			System.out.println("add expensefail");
			forwardPage = "failure";
		}
		return mapping.findForward(forwardPage);
	}

}
