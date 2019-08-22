package com.fer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fer.bean.Address;
import com.fer.bean.PersonalInfo;
import com.fer.bean.User;
import com.fer.form.PersonalInfoForm;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;

public class PersonalInfoAction extends DispatchAction{
	FamilyExpenseReportServiceInterface familyExpenseReportServiceInterface = new FamilyExpenseReportServiceImpl();
	
	public ActionForward personalName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	PersonalInfoForm personalInfoForm = (PersonalInfoForm) form;
		HttpSession session =request.getSession();
		return mapping.findForward("personalNameInfo");
	}

	public ActionForward personalContact(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		PersonalInfoForm personalInfoForm = (PersonalInfoForm) form;
		String firstName = personalInfoForm.getFirstName();
		String middleName = personalInfoForm.getMiddleName();
		String lastName = personalInfoForm.getLastName();

		session.setAttribute("firstName", firstName);
		session.setAttribute("middleName", middleName);
		session.setAttribute("lastName", lastName);


		return mapping.findForward("personalContact");
	}

	public ActionForward personalAddress(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		PersonalInfoForm personalInfoForm = (PersonalInfoForm) form;

		String email = personalInfoForm.getEmail();
		String phone = personalInfoForm.getPhone();

		session.setAttribute("email", email);
		session.setAttribute("phone", phone);


		return mapping.findForward("personalAddressInfo");
	}

	public ActionForward personalReview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		PersonalInfoForm personalInfoForm = (PersonalInfoForm) form;

		String streetLine = personalInfoForm.getStreetLine();
		String town = personalInfoForm.getTown();
		String city = personalInfoForm.getCity();
		String state = personalInfoForm.getState();
		String zip = personalInfoForm.getZip();
		String country = personalInfoForm.getCountry();

		session.setAttribute("streetLine", streetLine);
		session.setAttribute("town", town);
		session.setAttribute("city", city);
		session.setAttribute("state", state);
		session.setAttribute("zip", zip);
		session.setAttribute("country", country);

		return mapping.findForward("personalReviewInfo");
	}

	
	public ActionForward savePersonalInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		PersonalInfo personalInfo = (PersonalInfo) session.getAttribute("personalInfo");
		User user= new User();
		Address address = new Address();
		
		user.setUserName(session.getAttribute("userName").toString());     
		user.setFirstName(session.getAttribute("firstName").toString());
		user.setMiddleName(session.getAttribute("middleName").toString());
		user.setLastName(session.getAttribute("lastName").toString());
		user.setEmail(session.getAttribute("email").toString());
		user.setPhone(session.getAttribute("phone").toString());
		user.setId(personalInfo.getUser().getId());
		
		address.setStreetLine(session.getAttribute("streetLine").toString());
		address.setTown(session.getAttribute("town").toString());
		address.setCity(session.getAttribute("city").toString());
		address.setState(session.getAttribute("state").toString());
		address.setZip(Integer.parseInt(session.getAttribute("zip").toString()));
		address.setCountry(session.getAttribute("country").toString());
		address.setId(personalInfo.getAddress().getId());
		address.setUserId(personalInfo.getUser());
		boolean personalInfoFlag = familyExpenseReportServiceInterface.updatePersonalInfo(user, address);
		
		if(personalInfoFlag){
			return mapping.findForward("personalInfoSuccess");
			
		}
		else{
			return mapping.findForward("personalInfoFail");
		}
		
		
	}
}
