<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.fer.bean.ExpenseName"%>
<%@page import="com.fer.bean.ExpenseGroup"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceImpl"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceInterface"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@page import="com.fer.bean.Expense"%>
<%@page import="java.util.List"%>
<html>
<head>

<title>Family Maintenance Report - Edit Expense</title>
<script>
function submitForm(forwardPage) {
			var form = document.EditExpenseForm;
			form.action = forwardPage;
			form.submit();
		}
</script>
</head>

<body>

	<html:form action="editExpense?method=save">
		<%
			Expense expense = (Expense) session.getAttribute("expense");
			FamilyExpenseReportServiceInterface familServiceInterface = new FamilyExpenseReportServiceImpl();
			
			Map<String, ExpenseGroup> expenseGroups = familServiceInterface.getExpenseGroups();
			Map<String, ExpenseName> expenseNames = familServiceInterface.getExpenseNames();
		%>
		<table align='center'>
			<tr>
				<td colspan="2" align='center' style='color: green'><font
					size='5'>Edit Expense</font></td>

			</tr>
			<tr>
				<td colspan='2' align='left'>
					<div style="color: red">
						<html:errors />
					</div>
				</td>
			</tr>
			<tr>
				<td>Expense Type</td>
				<td>
					<select name="type">
						<%
						Set<String> groupKeySet = expenseGroups.keySet();
						Iterator<String> groupIterator = groupKeySet.iterator();
						while(groupIterator.hasNext()) {
							String groupKey = groupIterator.next();
							ExpenseGroup expenseGroup = expenseGroups.get(groupKey);
						%>
							<option value="<%=groupKey%>" 
								<%if(expense.getType().equals(expenseGroup.getId()+"")) {%>selected<%}%> >
								<%=expenseGroup.getName()%>
							</option>
						<%} %>
					</select>
				</td>
			</tr>
			<tr>
				<td>Expense Name</td>
				<td>
					<select name="name">
						<%
						Set<String> nameKeySet = expenseNames.keySet();
						Iterator<String> nameIterator = nameKeySet.iterator();
						while(nameIterator.hasNext()) {
							String groupKey = nameIterator.next();
							ExpenseName expenseName = expenseNames.get(groupKey);
						%>
							<option value="<%=expenseName.getId()%>" 
								<%if(expense.getName().equals(expenseName.getId()+"")) {%>selected<%}%> >
								<%=expenseName.getName()%>
							</option>
						<%} %>
					</select>
				</td>
			</tr>
			
			<tr>
				<td height='30'>Debited From:</td>
				<td>
					<html:select property="debitedFrom" name="AddExpenseForm" >
						<option value="C">Cash</option>
						<option value="B">Bank</option>
					</html:select>
				</td>
			</tr>
			
			<tr>
				<td>Date</td>
				<td><input type='text' name='date'
					value="<%=expense.getDate()%>"></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type='text' name='price'
					value="<%=expense.getPrice()%>"></td>
			</tr>
			<tr>
				<td>NumOfItems</td>
				<td><input type='text' name='numberOfItems'
					value="<%=expense.getNumberOfItems()%>"></td>
			</tr>

			<tr>
				<td>Total</td>
				<td><input type='text' name='total'
					value="<%=expense.getTotal()%>"></td>
			</tr>
			<tr>
				<td>ByWhom</td>
				<td><input type='text' name='byWhom'
					value="<%=expense.getByWhom()%>"></td>
			</tr>

			<tr>
				<td colspan='2' align='center'><html:submit value="Edit" /> <!-- <input type='button' value='Edit' onclick="submitForm('EditExpense')"> -->
				</td>
			</tr>
		</table>
		<input type="hidden" name="expenseId"
			value="<%=request.getParameter("expenseId") %>" />
	</html:form>
</body>
</html>