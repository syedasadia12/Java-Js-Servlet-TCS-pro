<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/styles.css">
<title>Home</title>
</head>
<body>
<%
		String sensitiveRole = "Cashier/Teller";
		if (session.getAttribute("username") == null) {
			request.setAttribute("messageType", "error");

			request.setAttribute("messageHeader", "Access Deny");

			request.setAttribute("messageDetail", "Please Login");

			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
			//session.setAttribute("errorMessage", "You do not have access to this page!");
	%>
<%@ include file="header.jsp" %>
<%@ include file="navbar.jsp" %>
<div class="main-content">
	<%@include file="msgContainer.jsp" %>
	<div class="content-wrapper">
		
	</div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>