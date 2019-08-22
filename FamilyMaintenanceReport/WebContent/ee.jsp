<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>

<title>Edit Expense</title>
<script>
function submitForm(forwardPage) {
			var form = document.EditExpenseForm;
			form.action = forwardPage;
			form.submit();
		}
</script>
</head>

<body>

	<html:form action="editExpense?method=update">
		<table border='1' align='center'>
			<tr>
				<td colspan="2">Edit Expense</td>

			</tr>
			<tr>
				<td colspan='2' align='left'>
					<div style="color: red">
						<html:errors />
					</div>
				</td>
			</tr>
			<tr>
				<td>ExpenseType</td>
				<td><html:text name="EditExpenseForm" property="type"
						value='${expense.expenseType}' /></td>
			</tr>

			<tr>
				<td>Date</td>
				<td><html:text name="EditExpenseForm" property="date"
						value='${expense.date}' /></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><html:text name="EditExpenseForm" property="price"
						value='${expense.price}' /></td>
			</tr>
			<tr>
				<td>NumOfItems</td>
				<td><html:text name="EditExpenseForm" property="numberOfItems"
						value='${expense.numOfItems}' /></td>
			</tr>

			<tr>
				<td>Total</td>
				<td><html:text name="EditExpenseForm" property="total"
						value='${expense.total}' /></td>
			</tr>
			<tr>
				<td>ByWhom</td>
				<td><html:text name="EditExpenseForm" property="byWhom"
						value='${expense.byWhom}' /></td>
			</tr>

			<tr>
				<td colspan='2' align='center'><html:submit value="Edit" /></td>
			</tr>
		</table>
		<input type="hidden" name="expenseId" value="<%=request.getParameter("Id") %>" />
	</html:form>
</body>
</html>