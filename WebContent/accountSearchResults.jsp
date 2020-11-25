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
</head>
<body>
	<%
		String sensitiveRole = "Cashier/Teller";
		if (session.getAttribute("username") == null) {
			request.setAttribute("messageType", "error");

			request.setAttribute("messageHeader", "Access Deny");

			request.setAttribute("messageDetail", "Only " + sensitiveRole + " has access to the page");

			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

		if (session.getAttribute("userType") == null || !(session.getAttribute("userType").equals(sensitiveRole))) {

			//session.setAttribute("errorMessage", "You do not have access to this page!");
	%>
	<%
		request.setAttribute("messageType", "error");

			request.setAttribute("messageHeader", "Access Deny");

			request.setAttribute("messageDetail", "Only " + sensitiveRole + " has access to the page");

			request.getRequestDispatcher("home.jsp").forward(request, response);

		}
	%>
	<%@ include file="header.jsp"%>
	<%@ include file="navbar.jsp"%>

	<div class="main-content">
		<div class="content-wrapper">
			<div>
				<h2 class="text-center">Account Status</h2>
			</div>

				<% 
				ArrayList<Account> acc=new ArrayList<Account>();
				acc = (ArrayList<Account>) request.getAttribute("account");
				
				
				%>

				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th>Customer ID</th>
							<th>Account ID</th>
							<th>Account Type</th>
							<th>Message</th>
							<th>Account Status</th>
							<th>Last Update</th>
							<th>Account Balance</th>
						</tr>
					</thead>
					<% if (acc==null|| acc.isEmpty()) { %>			
						<h3>No Records found!</h3>
					<% } else {%>
					<%for(Account myacc : acc ) { %>
					
					<tr>
						<td><%=myacc.getCustomerId()%></td>
						<td><%=myacc.getAccountId()%></td>
						<td><%=myacc.getAccountType()%></td>
						<td><%=myacc.getMessage()%></td>
						<td><%=myacc.getStatus()%></td>
						<td><%=myacc.getDate()%></td>
						<td><%=myacc.getBalance()%></td>
						
					</tr>
					<%} %>
					<%} %>
					</table>
					
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>