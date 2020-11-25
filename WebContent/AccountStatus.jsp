<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Added import statements -->
<%@ page import="com.ilp.bankmgr.bean.*"%>
<%@ page import="com.ilp.bankmgr.dao.*"%>
<%@ page import="com.ilp.bankmgr.service.*"%>
<%@ page import="com.ilp.bankmgr.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.*,java.text.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="styles/styles.css">
<title>All Account Status</title>


<%
String sensitiveRole = "Customer_Account_Executive";
if(session.getAttribute("username")==null){
request.setAttribute("messageType","error");
	
	request.setAttribute("messageHeader","Access Deny");
	
	request.setAttribute("messageDetail","Only "+sensitiveRole+" has access to the page");
	
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}



if(session.getAttribute("userType")==null || !(session.getAttribute("userType").equals(sensitiveRole))){
	
	//session.setAttribute("errorMessage", "You do not have access to this page!");%>
	<%
	
	request.setAttribute("messageType","error");
	
	request.setAttribute("messageHeader","Access Deny");
	
	request.setAttribute("messageDetail","Only "+sensitiveRole+" has access to the page");
	
	request.getRequestDispatcher("home.jsp").forward(request,response);
	
}
%>
</head>
<body>

	<%@ include file="header.jsp"%>
	<%@ include file="navbar.jsp"%>

	<div class="main-content">
	<%@ include file="msgContainer.jsp" %>
		<div class="content-wrapper">
			<div>
				<h2 class="text-center">Account Status</h2>
			</div>

				<% 
				ArrayList<Account> acc = AccountStatusService.searchAllAccounts();
				%>
			<div id="printDIV">
				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th class="th-sm">Customer ID</th>
							<th class="th-sm">Account ID</th>
							<th class="th-sm">Account Type</th>
							<th class="th-sm">Message</th>
							<th class="th-sm">Account Status</th>
							<th class="th-sm">Last Update</th>
							<th class="th-sm">Operations</th>
						</tr>
					</thead>
					
					<% if (acc.isEmpty()) { %>
						
						<h3>No Records found!</h3>
						<h4>Refresh the page for updated records.</h4>
					<% } else {%>
					<%for(Account myacc : acc ) { %>
					<tbody>
					<tr>

						<td><%=myacc.getCustomerId()%></td>
						<td><%=myacc.getAccountId()%></td>
						<td><%=myacc.getAccountType()%></td>
						<td><%=myacc.getMessage()%></td>
						<td><div><%=myacc.getStatus()%></div></td>
						<td>
							<%SimpleDateFormat dt = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss aa"); %>
							<%=dt.format(myacc.getDate())%>
						</td>
						
						<td>
							<div class="text-center"><button type="button" class="btn btn-link" onclick="location.reload()">
							Refresh</button>
							</div>
						</td>
					</tr>
					</tbody>
					<%} %>
					<%} %>
					</table>
				</div>
					<div class="text-center"><button type="button" class="btn btn-primary" onclick="window.location.reload();">
							Refresh the List</button></div>
							</br>
							<div class="text-center"><button type="button" class="btn btn-primary" onClick="javascript:ToPDFFile();">
							Print as PDF</button></div>
							</br>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
	<script language="javascript">
    function ToPDFFile() {
        var toPDF = document.getElementById('printDIV');
        var newOpenWindow = window.open('','_blank','width=700,height=300');
        newOpenWindow.document.open();
        newOpenWindow.document.write('<html><body onload="window.print()">' + toPDF.innerHTML + '</body></html>');
        newOpenWindow.document.close();
    }
</script>
</body>
</html>