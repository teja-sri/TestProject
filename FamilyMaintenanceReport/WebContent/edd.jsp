<%@page import="com.fer.bean.Expense"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
<head>
<script>
function submitForm(forwardPage) {
			var form = document.EditExpenseForm;
			form.action = forwardPage;
			form.submit();
		}
</script>
</head>
<body>
	<%
		List<Expense> expenses = (List<Expense>) session.getAttribute("expenses");
	%>
	<html:form action ="/editExpense?method=get">
		<font size=5>Edit Expense</font>
		<select name="Id">
			<%
				if(expenses != null) {
					for(Expense expense: expenses){
			%>
					<option value="<%=expense.getId()%>"><%=expense.getId()%><%=expense.getType()%></option>
			<%
					}
				}
			%>
		</select>
		<html:submit>submit</html:submit>
	</html:form>	
</body>
</html>

