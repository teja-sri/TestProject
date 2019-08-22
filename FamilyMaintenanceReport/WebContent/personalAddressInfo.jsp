<%@page import="com.fer.bean.Address"%>
<%@page import="com.fer.bean.PersonalInfo"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<head>
	<title>Family Maintenance Report - Personal Address</title>
</head>

<html:form action='personalInfo.do?method=personalReview'>
	<%
		PersonalInfo personalInfo = (PersonalInfo) session.getAttribute("personalInfo");
		if(personalInfo.getAddress() == null)	{
			personalInfo.setAddress(new Address());
		}
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
			<font size='5'>Address Details</font>
		</td>
		</tr>

		<tr>
			<td>Street Line</td>
			<td><input type='text' name='streetLine'
				value='<%=personalInfo.getAddress().getStreetLine()%>'></td>
		</tr>
		
		<tr>
			<td>Town</td>
			<td><input type='text'name='town' value='<%=personalInfo.getAddress().getTown()%>'></td>
		</tr>

		<tr>
			<td>City</td>
			<td><input type='text' name='city'
				value='<%=personalInfo.getAddress().getCity()%>'></td>
		</tr>

		<tr>
			<td>State</td>
			<td><input type='text' name='state'
				value='<%=personalInfo.getAddress().getState()%>'></td>
		</tr>

		<tr>
			<td>Zip</td>
			<td><input type='text' name='zip'
				value='<%=personalInfo.getAddress().getZip()%>'></td>
		</tr>

		<tr>
			<td>Country</td>
			<td><input type='text' name='country'
				value='<%=personalInfo.getAddress().getCountry()%>'></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<html:submit value="Review" />
			</td>
		</tr>
	</table>
</html:form>
