<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.fer.bean.ExpenseName"%>
<%@page import="com.fer.bean.ExpenseGroup"%>
<%@page import="java.util.List"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceImpl"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceInterface"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
<head>
<title>Family Maintenance Report - Add Expense</title>
<script>
	function submitForm(forwardPage) {
		var form = document.AddExpenseForm;
		form.action = forwardPage;
		form.submit();
	}
</script>
</head>
<body>
	<html:form action="/addExpense?method=save">
		<%
			FamilyExpenseReportServiceInterface familServiceInterface = new FamilyExpenseReportServiceImpl();
			Map<String, ExpenseGroup> expenseGroups = familServiceInterface.getExpenseGroups();
			Map<String, ExpenseName> expenseNames = familServiceInterface.getExpenseNames();
		%>
		<table align='center'>
			<tr>
				<td colspan='2' align='center' style='color: green' height='30px'>
					<font size='5'> Add Expense </font>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="color: red;">
						<%-- <html:errors /> --%>
					</div>
				</td>
			</tr>
			<tr>
				<td height='30'>Expense Type:</td>
				<td>
					<html:select property="type" name="AddExpenseForm">
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
					<html:select property="name" name="AddExpenseForm">
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
				<td height='30'>Debited From:</td>
				<td>
					<html:select property="debitedFrom" name="AddExpenseForm">
						<option value="C">Cash</option>
						<option value="B">Bank</option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td height='30'>Date:</td>
				<td><input type="text" name="date" id="datePicker"/>
				</td>
			</tr>
			<tr>
				<td height='30'>Price:</td>
				<td><html:text property="price" name="AddExpenseForm"></html:text>
				</td>
			</tr>
			<tr>
				<td>Number of Items:</td>
				<td><html:text property="numberOfItems" name="AddExpenseForm"></html:text>
				</td>
			</tr>
			<tr>
				<td height='30'>Total:</td>
				<td><html:text property="total" name="AddExpenseForm"></html:text>
				</td>
			</tr>
			<tr>
				<td height='30'>By Whom:</td>
				<td><html:text property="byWhom" name="AddExpenseForm"></html:text>
				</td>
			</tr>
			<tr>
				<td colspan='2' align='center'><html:submit>Add</html:submit></td>
			</tr>
		</table>
	</html:form>
</body>
</html>