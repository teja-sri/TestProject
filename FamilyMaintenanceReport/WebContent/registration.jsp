<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>

<title>Family Maintenance Report - Registration</title>
<script>
	function submitForm(forwardPage) {
		var form = document.forms[0];
		form.action = forwardPage;
		form.submit();
	}
	
	var request;  
	
	function validateEmail() {
		var email = document.forms[0].email.value;  
		var url="registrationAjax.jsp?email="+email;  
		  
		if(window.XMLHttpRequest){  
			request = new XMLHttpRequest();  
		}  
		else if(window.ActiveXObject){
			request = new ActiveXObject("Microsoft.XMLHTTP");  
		}  
		  
		try{  
			request.onreadystatechange = getInfo;  
			request.open("GET", url, true);  
			request.send();  
		}
		catch(e){
			alert("Unable to connect to server");
		}  
	}  
		  
	function getInfo(){  
		if(request.readyState == 4){  
			var response = request.responseText;
			alert(response);
			if(response.trim() == 'Y') {
				document.getElementById('emailStatus').style.color = 'green';
				document.getElementById('emailStatus').innerHTML = 'Email is available...';
			}
			else {
				document.getElementById('emailStatus').style.color = 'red';
				document.getElementById('emailStatus').innerHTML = 'Email is not available...';
			}
		}
	}  
	
</script>
</head>
<body>
	<html:form action="/registration" >
		<table width='300' align="center">
			<tr>
				<td colspan="2"><font size=15, color='green'>Registration</font>
				</td>
			</tr>
			<tr>
				<td colspan="2"><div style="color: red;">
						<html:errors />
					</div></td>
			</tr>
			<tr>
				<td height='40'>First Name:</td>
				<td><html:text property="firstName" name="RegistrationForm" />
				</td>
			</tr>

			<tr>
				<td height='40'>Last Name:</td>
				<td><html:text property="lastName" name="RegistrationForm" />
				</td>
			</tr>

			<tr>
				<td height='40'>Email:</td>
				<td>
					<html:text property="email" name="RegistrationForm" onchange="javascript: validateEmail()"/><BR>
					<DIV id="emailStatus"></DIV>
				</td>
			</tr>

			<tr>
				<td height='40'>UserName:</td>
				<td><html:text property="userName" name="RegistrationForm" />
				</td>
			</tr>

			<tr>
				<td height='40'>Password:</td>
				<td><html:text property="password" name="RegistrationForm" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><html:submit>Register</html:submit>
				</td>
			</tr>
		</table>

	</html:form>
</body>
</html>