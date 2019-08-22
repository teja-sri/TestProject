<%@page import="com.fer.bean.PersonalInfo"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<head>
	<title>Family Maintenance Report - Personal Name</title>
</head>

<html:form action='personalInfo.do?method=personalContact'>
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
			<font size='5'>Personal Details</font>
		</td>
		</tr>
		
		<tr>
		<td>First Name</td>
		<td>
			<input type='text'   name='firstName' value='<%=personalInfo.getUser().getFirstName()%>'>
		</td>
	</tr>

	<tr>
		<td>Last Name</td>
		<td>
			<input type='text'  name='lastName' value='<%=personalInfo.getUser().getLastName()%>'>
		</td>
	</tr>

	<tr>
		<td>Middle Name</td>
		<td>
			<input type='text' name='middleName' value='<%=personalInfo.getUser().getMiddleName()%>'> 
		</td>
	</tr>

	
		<tr>
			<td colspan="2" align="center">
			<html:submit value="Next" />      
				

			</td>
		</tr>
	</table>

</html:form>
