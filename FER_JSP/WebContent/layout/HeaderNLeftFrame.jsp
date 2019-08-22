<html>
	<head>
		<title>DashBoard</title>
		
		<script type="text/javascript">
			function submitForm(nextpath){
				var form = document.DashboardForm;
				form.action = nextpath;
				
				form.submit();
			}
		</script>
	</head>
	<body>
		<form action="" name="DashboardForm" method="post">
			<table border="1" align="center" height="500px" width="800px" cellSpacing="0" >
				<tr height="100px">
					<td colspan="2" align="center">Family Expenses Report User: ${username}
					
					</td>
				</tr>
				<tr>
					<td  width="200px">
						<a href="javascript: submitForm('AddExpense.jsp')">Add Expense</a><BR>
						<a href="javascript: submitForm('displayEditExpenseOptions')">Edit Expense</a><BR>
						<a href="javascript: submitForm('displayDeleteExpense')">Delete Expense</a><BR>
						<a href="javascript: submitForm('displayExpenseReport')">Expense Report</a><BR>
						<a href="javascript: submitForm('displayResetPassword')">Reset Password</a><BR>
						<a href="javascript: submitForm('displayPersonalName')">UpdateManual</a>
					</td>
					<td align ="center">