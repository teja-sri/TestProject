<%@page import="com.fer.service.FamilyExpenseReportServiceImpl"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceInterface"%>
<%
	String email = request.getParameter("email");
	
	FamilyExpenseReportServiceInterface familyExpenseReportServiceInterface = new FamilyExpenseReportServiceImpl();
	boolean isEmailAvailable = familyExpenseReportServiceInterface.isEmailAvailable(email);
	
 	out.print((isEmailAvailable) ? "Y" : "N");
%>