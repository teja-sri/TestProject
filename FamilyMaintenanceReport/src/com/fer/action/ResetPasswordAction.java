package com.fer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;

import com.fer.bean.PersonalInfo;
import com.fer.bean.UserSession;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class ResetPasswordAction extends DispatchAction{
	
	public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("resetPassword");
	}
		
		public ActionForward reset(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		FamilyExpenseReportServiceInterface familyExpenseReportServiceInterface = new FamilyExpenseReportServiceImpl();
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession)session.getAttribute(id);

		DynaValidatorForm dynaValidatorActionForm = (DynaValidatorForm) form;

	    PersonalInfo validateUserFlag=familyExpenseReportServiceInterface.login(session.getAttribute("userName").toString(), dynaValidatorActionForm.get("currentPassword").toString());
	 	
	    
	    if(validateUserFlag!= null){
	 	boolean ResetPasswordFlag = familyExpenseReportServiceInterface.resetPassword(session.getAttribute("userName").toString(), dynaValidatorActionForm.get("newPassword").toString());
	             
	    	return mapping.findForward("resetSuccess");
	    }
	    
	     else {
		return mapping.findForward("resetFail");
	}

}

}
