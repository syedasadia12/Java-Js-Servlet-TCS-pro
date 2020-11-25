<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/styles.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	
<title>Account Search</title>
<style>
body {
	background-color: #e6e6fa;
}

.update-form {
	width: 350px;
	margin: 0 auto;
	font-size: 20px;
}

.update-form form {
	
	background: #f7f7f7;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.update-form h2 {
	margin: 0 0 15px;
}

.form-control, .btn {
	min-height: 38px;
	border-radius: 2px;
}

.btn {
	font-size: 15px;
	font-weight: bold;
}

.warning {
	font-size: 15px;
	font-weight: bold;
	color: red;
}
.error {
	font-size: 20px;
	font-weight: bold;
	color: red;
}
</style>
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
<%@ include file="header.jsp" %>
<%@ include file="navbar.jsp" %>
<div class="main-content">
<%@ include file="msgContainer.jsp" %>
<div class="update-form" style="text-align:center;">

<h1>Account Search</h1>
<form name="form" id="form" method="POST" action="searchAccount">
  <label for="caccid">Enter Account ID:</label><br>
  <input type="number" id="caccid" name="caccid"><br>
  <br>or<br><br>
  
  <label for="cid">Enter Customer ID:</label><br>
  <input type="number" id="cid" name="cid"><br><br>
  <input type="submit" value="Submit">
</form>
</div>
</div>
<%@ include file="footer.jsp" %>

</body>
</html>
