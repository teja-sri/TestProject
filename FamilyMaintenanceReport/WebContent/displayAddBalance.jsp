
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>

<head>
	<title>Family Maintenance Report - Add Balance</title>
</head>

<body>

	<html:form action="/updateBalance?method=saveBalance">
		<table align="center">
			<tr>
				<td colspan='2' align='center' style='color: green' height='30px'>
					<font size='5'>Add Balance Information </font>
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
				<td>Balance Type</td>
				<td>
					<html:select name='BalanceForm' property='type'>
						<html:option value="B">Bank</html:option>
						<html:option value="C">Cash</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td>Balance Amount</td>
				<td>
					<html:text name='BalanceForm' property='amount'/>
				</td>
			</tr>
			<tr>
				<td>Action Type</td>
				<td>
					<html:select name='BalanceForm' property='actionType'>
						<html:option value="C">Credit</html:option>
						<html:option value="D">Debit</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan='2' align='center'><html:submit value="Save" /></td>
			</tr>
		</table>
	</html:form>
</body>
</html>