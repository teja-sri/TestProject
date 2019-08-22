<%@page import="com.fer.bean.PersonalInfo"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<head>
	<title>Family Maintenance Report - Personal Contact</title>
</head>

<html:form action='personalInfo.do?method=personalAddress'>
	<%
		PersonalInfo personalInfo = (PersonalInfo) session.getAttribute("personalInfo");
	%>
	<table align='center'>

		<tr>
			<td colspan='2' align='left'>
				<div style="color: red">
					<html:errors />
				</div>
			</td>
		</tr>

		<tr height='50px'>
			<td colspan='2' align='center' style='color: green' height='30px'>
			<font size='5'>Contact Details</font>
		</td>
		</tr>

		<tr>
		<td>Email</td>
		<td><input type='text'  name="email"
			value='<%=personalInfo.getUser().getEmail()%>'></td>
	</tr>

	<tr>
		<td>Phone</td>
		<td><input type='text' name='phone'
			value='<%=personalInfo.getUser().getPhone()%>'></td>
	</tr>

		<tr>
			<td colspan="2" align="center">
			<html:submit value="Next" />      
			</td>
		</tr>
	</table>
</html:form>