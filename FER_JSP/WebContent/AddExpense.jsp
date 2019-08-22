<jsp:include page="layout/HeaderNLeftFrame.jsp" />

							<table border="1px" align="center" cellSpacing="0" cellPadding="2px">
							<tr>
								<td colspan="2" align="center">Add Expense</td>
							</tr>
							<tr>
								<td>Expense Type</td>
								<td><input type ="text" name="expenseType"></td>
							</tr>
							<tr>
								<td>Date</td>
								<td><input type ="text" name="date"></td>
							</tr>
							<tr>
								<td>Price</td>
								<td><input type ="text" name="price"></td>
							</tr>
							<tr>
								<td>Number of Items</td>
								<td><input type ="text" name="numberOfItems"></td>
							</tr>
							<tr>
								<td>Total</td>
								<td><input type ="text" name="total"></td>
							</tr>
							<tr>
								<td> By Whom </td>
								<td><input type ="text" name="byWhom"></td>
							</tr>
							<tr>
								<td colspan="2" align="center" >
								<a href="javascript: submitForm('AddExpensePost.jsp')" >Save</a>
								</td>
							</tr>	
						</table>
					
					<jsp:include page="layout/Footer.html" />