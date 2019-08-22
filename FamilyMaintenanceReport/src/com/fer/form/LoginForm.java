package com.fer.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm{
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.userName = "Enter username";
		this.password = "Enter password";
	}
	/*@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		
		if(getUserName() == null || getUserName().trim().equals("")){
			actionErrors.add("userNameError", new ActionMessage("loginform.userName.required"));
		}
		if(getPassword() == null || getPassword().trim().equals("")){
			actionErrors.add("passwordError", new ActionMessage("loginform.password.required"));
		}
		return actionErrors;
	}*/
	
	
}
