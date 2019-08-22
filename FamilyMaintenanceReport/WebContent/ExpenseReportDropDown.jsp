
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="com.fer.bean.ExpenseName"%>
<%@page import="com.fer.bean.ExpenseGroup"%>
<%@page import="java.util.Map"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceImpl"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceInterface"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
<head>
	<title>Family Maintenance Report - Expense Report Selection</title>
</head>
<body>

	<html:form action="/expenseReport?method=list">
		<%
			
			FamilyExpenseReportServiceInterface familServiceInterface = new FamilyExpenseReportServiceImpl();
			Map<String, ExpenseGroup> expenseGroups = familServiceInterface.getExpenseGroups();
			Map<String, ExpenseName> expenseNames = familServiceInterface.getExpenseNames();
		%>
		<table align="center">
			<tr>
				<td colspan='2' align='center' style='color: green' height='30px'>
			<font size='5'>Expense Report Selection </font>
		</td>
			</tr>
			<tr>
				<td colspan='2' align='center'>
					<div id='errorDiv' style='color: red;'>
						<html:errors />
					</div>
				</td>
			</tr>
			<tr>
				<td height='30'>Expense Type:</td>
				<td>
					<html:select property="expenseType" name="ExpenseReportForm">
						<html:option value="ALL">ALL</html:option>
						<%
						Set<String> groupKeySet = expenseGroups.keySet();
						Iterator<String> groupIterator = groupKeySet.iterator();
						while(groupIterator.hasNext()) {
							String groupKey = groupIterator.next();
							ExpenseGroup expenseGroup = expenseGroups.get(groupKey);
						%>
							<option value="<%=expenseGroup.getId()%>"><%=expenseGroup.getName()%></option>
						<%} %>
					</html:select>
				</td>
			</tr>
			<tr>
				<td height='30'>Expense Name:</td>
				<td>
					<html:select property="expenseName" name="ExpenseReportForm">
						<html:option value="ALL">ALL</html:option>
						<%
						Set<String> nameKeySet = expenseNames.keySet();
						Iterator<String> nameIterator = nameKeySet.iterator();
						while(nameIterator.hasNext()) {
							String groupKey = nameIterator.next();
							ExpenseName expenseName = expenseNames.get(groupKey);
						%>
							<option value="<%=expenseName.getId()%>"><%=expenseName.getName()%></option>
						<%} %>
					</html:select>
				</td>
			</tr>
			<tr>
				<td>By whom</td>
				<td>
					<html:select name='ExpenseReportForm' property='byWhom'>
						<html:option value="ALL">ALL</html:option>
						<html:option value="Sateesh">sateesh</html:option>
						<html:option value="Kalpana">Kalpana</html:option>
						<html:option value="Manideep">Manideep</html:option>
						<html:option value="Vikas">Vikas</html:option>
					
					
						
					
					</html:select>
				</td>
			</tr>
			<tr>
				<td>FromDate</td>
				<td><html:text name='ExpenseReportForm' property='fromDate' /></td>
			</tr>
			<tr>
				<td>EndDate</td>
				<td><html:text name='ExpenseReportForm' property='toDate' /></td>
			</tr>
			<tr>
				<td colspan='2' align='center'><html:submit value="Get Report" />
				</td>
			</tr>

		</table>
	</html:form>
</body>
</html>