
<%@page import="com.rs.fer.service.ServiceImpl"%>
<%@page import="com.rs.fer.service.Service"%>
<%

Service service = new ServiceImpl();


	String username = request.getParameter("userName");
	String password = request.getParameter("passWord");
	
	int userId =  service.login(username, password);
	
	if(userId > 0) {
		
		session.setAttribute("username", username);
		session.setAttribute("userId", userId);
%>		
		<jsp:include page="layout/HeaderNLeftFrame.jsp" />
<%		
		out.println("Welcome to the user:  "+username);
%>		
		<jsp:include page="layout/Footer.html" />
<%		
	}else {
		out.println("invalid username/password.. try again..");
%>
		<jsp:include page="Login.html" />		
<%		
	}


%>