<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%--<%@page import="com.fer.bean.UserSession"%>
 <%
	String id = request.getParameter("id");
	UserSession userSession = (UserSession)session.getAttribute(id);
%> --%>
<html>
<head>
	 <!-- <script>
		function submitForm(forwardPage) {
			var form = document.forms[0];
			form.action = forwardPage;
			form.method.value="reset";
			form.submit();
		}
	</script> -->
	<script>
			function submitForm(forwardPage) {
			var form = document.AddExpenseForm;
			form.action = forwardPage;
			form.submit();
		}
	</script>
	
	<title>Family Maintenance Report - Reset Password</title>

</head>
 
<body>
	<html:form action="resetPassword?method=reset">
		<table align='center'>
			<tr>
				<td colspan='2' align='center'><font size=5, color='green'>Reset
						Password</font>
			</tr>
			<tr height='30'>
				<td>Current Password:</td>
				<td><html:text name="ResetPasswordForm"
						property="currentPassword" /></td>
			</tr>
			<tr height='30'>
				<td>New Password:</td>
				<td><html:text name="ResetPasswordForm" property="newPassword" />
				</td>
			</tr>
			<tr height='30'>
				<td>Confirm Password:</td>
				<td><html:text name="ResetPasswordForm"
						property="confirmPassword" /></td>
			</tr>
			<tr>
				<td colspan='2' align='center'>
					<!-- <input type="button"  value="Reset" onclick="submitForm('resetPassword')"> -->
				<html:submit>Reset Password</html:submit>
				</td>
			</tr>
		</table>
		<!-- <input type="hidden" name="method" value=""> -->
		
	</html:form>
</body>
</html>