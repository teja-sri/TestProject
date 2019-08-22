

<%@page import="com.rs.fer.pojo.Expense"%>
<%@page import="com.rs.fer.service.ServiceImpl"%>
<%@page import="com.rs.fer.service.Service"%>
<%

Service service = new ServiceImpl();

	
	Expense expense = new Expense();
	expense.setExpenseType(request.getParameter("expenseType"));
	expense.setNo_of_items(Integer.parseInt(request.getParameter("numberOfItems")));
	expense.setPrice(Float.parseFloat(request.getParameter("price")));
	expense.setTotal(Float.parseFloat(request.getParameter("total")));
	expense.setBy_whom(request.getParameter("byWhom"));
	expense.setDate(request.getParameter("date"));
	
	expense.setUserid(Integer.parseInt(session.getAttribute("userId").toString()));

	boolean isRecordAdded = service.addExpense(expense);
%>

<jsp:include page="layout/HeaderNLeftFrame.jsp" />
<%		
out.println(isRecordAdded ? "Expense added successfully.." : "Expense failed to add..");
%>
<jsp:include page="layout/Footer.html" />