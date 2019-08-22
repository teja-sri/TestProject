<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
<head>
	<title>Family Maintenance Report - Login</title>
<script>
function submitForm(forwardPage) {
			var form = document.LoginForm;
			form.action = forwardPage;
			form.submit();
		}
</script>
</head>
<body>
	<html:form action ="/login">	
		<table align="center">
			<tr>
				<td colspan = "2">
			
					<font size=15 color="green">Login</font>
				</td>	
			</tr>
			<tr>
				<td colspan = "2"><div style="color:red;">
					<html:errors/>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30">User Name:</td>
				<td>
				<html:text property="userName" name="LoginForm"></html:text>
				</td>
			</tr>
			<tr>
				<td height="30">Password:</td>
				<td>
				<html:password property="password" name="LoginForm"/>
				</td>
			</tr>
			<tr>
				<td>
				<html:submit>Login</html:submit>
				</td>
				<td>
				<input type="button" value="Register" onclick = "submitForm('registration.jsp')">
				</td>
				</tr>
				
		</table>
		<input type="hidden" name="ID" value="<%=System.currentTimeMillis()%>">
		</html:form>
</body>
</html>
