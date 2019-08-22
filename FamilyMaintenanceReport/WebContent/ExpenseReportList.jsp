<%@page import="com.fer.bean.ExpenseName"%>
<%@page import="com.fer.bean.ExpenseGroup"%>
<%@page import="java.util.Map"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceImpl"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceInterface"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.fer.bean.Expense" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.ArrayList" %>
<head>
	<title>Family Maintenance Report - Expense Report</title>

	<script>
		function submitForm(action, method, expenseId) {                            
	 		var form = document.ExpenseReportForm;
	
	 		form.expenseId.value = expenseId;
	 		form.method.value = method;
			form.action = action;                                          
			form.submit();
		}
		
		function submitReportForm(action, method, pageIndex) {                            
	 		var form = document.ExpenseReportForm;
	
	 		form.pageIndex.value = pageIndex;
	 		form.method.value = method;
	 		form.method = 'POST';
			form.action = action;                                          
			form.submit();
		}
	</script>
</head>
	<form action="expenseReport?method=update" name="ExpenseReportForm" method="POST">
	<% 
		HttpSession session1 = request.getSession();
		ArrayList<Expense> expenses = (ArrayList<Expense>) session1.getAttribute("expenses");
		Iterator<Expense> iterator = expenses.iterator();
		
		FamilyExpenseReportServiceInterface familServiceInterface = new FamilyExpenseReportServiceImpl();
		Map<String, ExpenseGroup> expenseGroups = familServiceInterface.getExpenseGroups();
		Map<String, ExpenseName> expenseNames = familServiceInterface.getExpenseNames();
		
		int numberOfRecords = 0;
		int numberOfRecordsPerPage = 100;
		int pageIndex = (request.getParameter("pageIndex") == null) ? 1 : Integer.parseInt(request.getParameter("pageIndex"));
		int totalNumberOfExpenses = familServiceInterface.totalExpensesCount
			(request.getParameter("expenseType"), request.getParameter("expenseName"), request.getParameter("byWhom"), 
			request.getParameter("fromDate"), request.getParameter("toDate"), numberOfRecords, numberOfRecordsPerPage, pageIndex);
	%>
		<table align='center' border="1">
			<tr>
				<td colspan='10' align='center' style='color: green'>
					<font size='5'>Expense Report</font>
				</td>
			</tr>
			<tr>
				<td><b>ID</b></td>
				<td><b>Group</b></td>
				<td><b>Name</b></td>
				<td><b>Date</b></td>
				<td><b>Price</b></td>
				<td><b>Number of items</b></td>
				<td><b>Total</b></td>
				<td><b>By whom</b></td>
				<td colspan="2"><b>Action</b></td>
			</tr>

			
			<%
			Double total = 0.0d;
			while (iterator.hasNext()) {
			Expense expense = iterator.next();
			total += expense.getTotal();
%>		
			<tr>
			<td><%=expense.getId()%></td>
			<td><%=expenseGroups.get(expense.getType()).getName()%></td>
			<td><%=expenseNames.get(expense.getName()).getName()%></td>
			<td><%=expense.getDate()%></td>
			<td><%=expense.getPrice()%></td>
			<td><%=expense.getNumberOfItems()%></td>
			<td><%=expense.getTotal()%></td>
			<td><%=expense.getByWhom()%></td>
			
			<td><a href = 'expenseReport.do?method=update&Id=<%=expense.getId()%>'>Edit</a></td>
			
			<td><a href = 'expenseReport.do?method=delete&Id=<%=expense.getId()%>'>Delete</a></td>
			</tr>
<%
	}
%>
			<tr>
				<td colspan="10" style="padding-left:410px;">
					<%=total%>
				</td>
			</tr>
			<tr>
				<td colspan="10" align="center">
					<%
					int noOfPages = totalNumberOfExpenses/numberOfRecordsPerPage;
					if(totalNumberOfExpenses > (noOfPages * numberOfRecordsPerPage)) {
						noOfPages = noOfPages + 1;
					}
					 
					for(int i=1;i<=noOfPages;i++) {
						String myurl = "javascript: submitReportForm('expenseReport.do','list','"+i+"')";
						if(i == pageIndex) {
					%>
						<%=i%>
					<%	}
						else {	%>
						<a href="<%=myurl%>"><%=i%></a><%if(i != noOfPages){%> <%}%>
						
					<%
						}
					}
					%>
				</td>
			</tr>					
		</table>
		<input type="hidden" name="method" value="">
		
		<input type="hidden" name="expenseType" value="<%=request.getParameter("expenseType")%>" >
		<input type="hidden" name="expenseName" value="<%=request.getParameter("expenseName")%>" >
		<input type="hidden" name="byWhom" value="<%=request.getParameter("byWhom")%>" >
		<input type="hidden" name="fromDate" value="<%=request.getParameter("fromDate")%>" >
		<input type="hidden" name="toDate" value="<%=request.getParameter("toDate")%>" >
		<input type="hidden" name="pageIndex" value="<%=request.getParameter("pageIndex")%>" >
		
	</form>


