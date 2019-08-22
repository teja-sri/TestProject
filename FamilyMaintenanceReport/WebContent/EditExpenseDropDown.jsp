<%@page import="com.fer.bean.ExpenseName"%>
<%@page import="java.util.Map"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceImpl"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceInterface"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.fer.bean.Expense"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>

<head>
	<title>Family Maintenance Report - Expense Options</title>
</head>

<body>

	<html:form action="/editExpense?method=get">
	<%
		List<Expense> expenses = (List<Expense>) session.getAttribute("expenses");
	
		FamilyExpenseReportServiceInterface familServiceInterface = new FamilyExpenseReportServiceImpl();
	
		Map<String, ExpenseName> expenseNames = familServiceInterface.getExpenseNames();
	%>
		<table align='center'>

			<tr>
				<td colspan='2' align='center' style='color: green'>
				<font size='5'>Edit Expense</font>
				</td>
			</tr>
			<tr>
				<td colspan='2' align='left'>
					<div style="color: red">
						<html:errors />
					</div>
				</td>
			</tr>

			<tr>
				<td>Edit Expense</td>
				<td>
				<%-- <html:select property="id">
						<c:forEach items="${expenses}" var="expense">
							<html:option  value="${expense.id}">${expense.id},${expense.total},${expense.expenseType}</html:option>
						</c:forEach>
				</html:select> --%>
			<select name="Id">
				<%
				
					for(Expense expense: expenses){
				%>
					<option value="<%=expense.getId()%>">
						<%=expenseNames.get(expense.getName()+"").getName()%>-<%=expense.getDate()%>-<%=expense.getTotal()%>-<%=expense.getByWhom()%>
					</option>
				<%
					}
				
				%>
		  </select>
		</tr>

			<tr>
				<td colspan='2' align='center'><html:submit value="Next" /></td>
			</tr>
		</table>
	</html:form>
</body>
</html>