
<%@page import="com.rs.fer.pojo.User"%>
<%@page import="com.rs.fer.service.ServiceImpl"%>
<%@page import="com.rs.fer.service.Service"%>
<%
	Service service = new ServiceImpl();

	User user = new User();
	user.setFirstName(request.getParameter("firstName"));
	user.setMiddleName(request.getParameter("middleName"));
	user.setLastName(request.getParameter("lastName"));
	user.setUserName(request.getParameter("userName"));
	user.setPassword(request.getParameter("passWord"));
	user.setEmail(request.getParameter("email"));
	user.setMobile(request.getParameter("mobile"));

	boolean isRegistered = service.register(user);
	String nextPath = "";

	if (isRegistered) {
		out.println("Registered Successfully..please try with login..<br>");
		nextPath = "Login.html";
	} else {
		out.println("Registration failed..try again..<br>");
		nextPath = "Registration.html";
	}
%>

<jsp:include page="<%=nextPath%>" />
