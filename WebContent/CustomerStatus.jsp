<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilp.bankmgr.bean.Customer"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ilp.bankmgr.service.*"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/styles.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Customer Status</title>


</head>
<body>
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
	<%@ include file="header.jsp"%>
	<%@ include file="navbar.jsp"%>

	<div class="main-content">
		<%@ include file="msgContainer.jsp"%>
		<div class="content-wrapper">
			<div>
				<h2 class="text-center">Customer Status</h2>
			</div>

			<%
				ArrayList<Customer> cust = (ArrayList<Customer>) request.getAttribute("cust");
			%>

			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>Customer ID</th>
						<th>SSN</th>
						<th>Name</th>
						<th>Age</th>
						<th>Address</th>
						<th>State</th>
						<th>City</th>
						<th>Message</th>
						<th>Status</th>
					</tr>
				</thead>

				<%
					for (Customer mycust : cust) {
				%>

				<tr>
					<td><%=mycust.getCustomerId()%></td>
					<td><%=mycust.getSsn()%></td>
					<td><%=mycust.getName()%></td>
					<td><%=mycust.getAge()%></td>
					<td><%=mycust.getAddress()%></td>
					<td><%=mycust.getState()%></td>
					<td><%=mycust.getCity()%></td>
					<td><%=mycust.getMessage()%></td>
					<td>Active</td>
					<td><div class="text-center">
							<button type="button" class="btn btn-link"
								onclick="window.location.reload();">Refresh</button>
						</div></td>
				</tr>
				<%
					}
				%>

			</table>
			<div class="text-center">
				<button type="button" class="btn btn-link"
					onclick="window.location.reload();">Refresh the List</button>
			</div>
		</div>
	</div>


	<%@ include file="footer.jsp"%>
</body>
</html>