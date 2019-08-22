<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<head>
	<title>Family Maintenance Report - Personal Review</title>
</head>

<html:form action='personalInfo.do?method=savePersonalInfo'>
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
			<font size='5'>Personal Information Review</font>
		</td>
		</tr>

		<tr>
			<td>First Name:</td>
			<td>
				<%=session.getAttribute("firstName").toString() %>
			</td>
		</tr>

		<tr>
			<td>Last Name:</td>
			<td>
				<%=session.getAttribute("lastName").toString() %>
			</td>
		</tr>
		
		<tr>
			<td>Middle Name:</td>
			<td>
				<%=session.getAttribute("middleName").toString() %>
			</td>
		</tr>
		
		<tr>
			<td>Email:</td>
			<td>
				<%=session.getAttribute("email").toString() %>
			</td>
		</tr>
		
		<tr>
			<td>Phone</td>
			<td>
				<%=session.getAttribute("phone").toString() %>
			</td>
		</tr>
		
		<tr>
			<td>Street Line:</td>
			<td>
				<%=session.getAttribute("streetLine").toString() %>
			</td>
		</tr>
		
		<tr>
			<td>Town:</td>
			<td>
				<%=session.getAttribute("town").toString() %>
			</td>
		</tr>
		
		<tr>
			<td>City:</td>
			<td>
				<%=session.getAttribute("city").toString() %>
			</td>
		</tr>
		
		<tr>
			<td>State:</td>
			<td>
				<%=session.getAttribute("state").toString() %>
			</td>
		</tr>
		
		<tr>
			<td>Zip:</td>
			<td>
				<%=session.getAttribute("zip").toString() %>
			</td>
		</tr>
		
		<tr>
			<td>Country:</td>
			<td>
				<%=session.getAttribute("country").toString() %>
			</td>
		</tr>
	
		<tr>
			<td colspan="2" align="center">
             <html:submit value="save"/>
			</td>
		</tr>
	</table>
</html:form>