package com.fer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.fer.bean.PersonalInfo;
import com.fer.bean.UserSession;
import com.fer.form.LoginForm;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class LoginAction extends Action{

	static Logger logger = Logger.getLogger(LoginAction.class);
	
	FamilyExpenseReportServiceInterface familyExpenseReportServiceInterface = new FamilyExpenseReportServiceImpl();
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DynaValidatorForm loginForm = (DynaValidatorForm) form;
		PersonalInfo personalInfo = familyExpenseReportServiceInterface.login(loginForm.get("userName").toString(), loginForm.get("password").toString());

		System.out.println("login flag: " + (personalInfo != null) + " of user name: "
				+ loginForm.get("userName"));
		logger.info("login flag: " + (personalInfo != null) + " of user name: "
				+ loginForm.get("userName"));
		
		String forwardPage = "";
		if (personalInfo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", loginForm.get("userName"));
			session.setAttribute("password", loginForm.get("password"));
			session.setAttribute("personalInfo", personalInfo);	
			
			/*String id = request.getParameter("ID");
 			session.setAttribute("Id", id);*/
 			UserSession userSession = new UserSession();
 			userSession.setUsername(loginForm.get("userName").toString());
 			userSession.setPasword(loginForm.get("password").toString());
 			userSession.setImagePath(personalInfo.getUser().getImagePath());
 			userSession.setPersonalInfo(personalInfo);
 			if(personalInfo.getUser() != null) {
 				userSession.setImagePath(personalInfo.getUser().getImagePath());
 			}
 			session.setAttribute("userSession", userSession);
			forwardPage = "success";
		} else {
			forwardPage = "failure";
		}
		return mapping.findForward(forwardPage);
	}
	

}
