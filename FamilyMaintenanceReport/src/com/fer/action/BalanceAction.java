package com.fer.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;

import com.fer.bean.Balance;
import com.fer.bean.PersonalInfo;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class BalanceAction extends DispatchAction{
	
	FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();
	
	public ActionForward displayAddBalance(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		return mapping.findForward("displayAddBalance");
	}
	
	public ActionForward saveBalance(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		DynaValidatorForm dynaValidatorForm = (DynaValidatorForm) form;
		HttpSession session = request.getSession();
		
		PersonalInfo personalInfo = ((PersonalInfo)session.getAttribute("personalInfo"));
		int userId = personalInfo.getUser().getId();
		
		Balance balance = new Balance();
		balance.setType(dynaValidatorForm.getString("type"));
		balance.setAmount(Float.parseFloat(dynaValidatorForm.get("amount").toString()));
		balance.setActionType(dynaValidatorForm.getString("actionType"));
		balance.setDate(new Date());
		balance.setUserId(userId);
		
		boolean saveBalanceFlag = familyExpenseReportService.saveBalance(balance);
		session.setAttribute("SAVE_BALANCE_STATUS", 
			(saveBalanceFlag) ? "Balance added successfully" : "Balance add failed");
		
		return mapping.findForward("saveBalanceStatus");
	}
	
}
