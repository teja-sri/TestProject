<%@page import="com.fer.bean.Balance"%>
<%@page import="java.util.Map"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceImpl"%>
<%@page import="com.fer.service.FamilyExpenseReportServiceInterface"%>
<%@page import="com.fer.bean.UserSession"%>
<%
	//String id = request.getParameter("ID");
	//String id = session.getAttribute("Id").toString();
	UserSession userSession = (UserSession) session.getAttribute("userSession");
	
	if(userSession == null || userSession.getUsername() == null)	{
		response.sendRedirect("login.jsp");
	}
	
	FamilyExpenseReportServiceInterface familServiceInterface = new FamilyExpenseReportServiceImpl();
	Map<String, Balance> balances = familServiceInterface.getBalances();
	
	float bankBalance = 0.0f;
	float cashBalance = 0.0f;
	if(balances != null && !balances.isEmpty())	{
		bankBalance = (balances.containsKey("B")) ? balances.get("B").getAmount() : 0.0f;
		cashBalance = (balances.containsKey("C")) ? balances.get("C").getAmount() : 0.0f;
	}
%>

<table>
	<tr>
		<td colspan="2" align="center"
			style="color: green; padding-left: 260px;"><font size="5">Family
				Maintenace Report</font></td>
		<td align="right" style="padding-left: 158px;"><a
			href="login.jsp">Logout</a></td>
	</tr>
</table>
<table>
	<tr>
		<td colspan="2" align="left" width="500px"><img
			alt="your uploaded image"
			src="<%="upload/"+userSession.getImagePath()%>" width="80px"
			height="80px"></td>
		<td align="right" style="text-align: right;">
			<table>
				<tr>
					<td>User: <%=session.getAttribute("userName") %>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right: 0px;">Bank Balance: <%=bankBalance%><br>
						Cash Balance: <%=cashBalance%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>