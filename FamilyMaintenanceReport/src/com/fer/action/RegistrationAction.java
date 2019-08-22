package com.fer.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fer.bean.User;
import com.fer.form.RegistrationForm;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class RegistrationAction extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FamilyExpenseReportServiceInterface familyExpenseReportServiceInterface = new FamilyExpenseReportServiceImpl();
		RegistrationForm registrationForm = (RegistrationForm) form;
		
		User user = new User();
	    user.setFirstName(registrationForm.getFirstName());
	    user.setLastName(registrationForm.getLastName());
	    user.setEmail(registrationForm.getEmail());
	    user.setUserName(registrationForm.getUserName());
	    user.setPassword(registrationForm.getPassword());
		
		boolean registerFlag = familyExpenseReportServiceInterface.registration(user);
		System.out.println("registerFlag: "+ registerFlag);
		
		String forwardPage="";
		if (registerFlag) {
			
			//out.println("Registration was success and please login using your credentials...");
			
			//requestDispatcher.include(request, response);
			forwardPage = "success";
		
			
		} else {
			//Fail
			forwardPage ="failure";
		}

		return mapping.findForward(forwardPage);
	}
	
}
