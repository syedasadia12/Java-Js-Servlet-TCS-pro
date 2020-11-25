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

<title>All Customer Status</title>
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
				<h2 class="text-center">Customer Status</h2>
			</div>

				<% 
				Customer c= (Customer)request.getAttribute("customer");
				
				
				%>

				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th>Customer ID</th>
							<th>SSN ID</th>
							<th>Name</th>
							<th>Age</th>
							<th>Address</th>
							<th>City</th>
							<th>State</th>
							<th>Message</th>
							<th>Last Update</th>
						</tr>
					</thead>
					<% if (c==null) { %>			
						<h3>No Records found!</h3>
					<% } else {%>
					
					<tr>
						<td><%=c.getCustomerId()%></td>
						<td><%=c.getSsn()%></td>
						<td><%=c.getName()%></td>
						<td><%=c.getAge()%></td>
						<td><%=c.getAddress()%></td>
						<td><%=c.getCity()%></td>
						<td><%=c.getState()%></td>
						<td><%=c.getMessage()%></td>
						<td><%=c.getDate()%></td>
						
					</tr>
					<%} %>
					</table>
					
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>