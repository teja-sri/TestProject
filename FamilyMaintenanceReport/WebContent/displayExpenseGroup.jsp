
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>

<head>
	<title>Family Maintenance Report - Expense Group</title>
</head>

<body>

	<html:form action="/updateExpenseRelated?method=saveExpenseGroup">
		<table align="center">
			<tr>
				<td colspan='2' align='center' style='color: green' height='30px'>
					<font size='5'>Expense Group Information </font>
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
				<td>Expense Group</td>
				<td><html:text name='ExpenseRelatedForm'
						property='expenseGroup' /></td>
			</tr>
			<tr>
				<td colspan='2' align='center'><html:submit value="Save" /></td>
			</tr>
		</table>
	</html:form>
</body>
</html>