<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.ilp.bankmgr.bean.*"%>
<%@ page import="com.ilp.bankmgr.dao.*"%>
<%@ page import="com.ilp.bankmgr.service.*"%>
<%@ page import="com.ilp.bankmgr.servlet.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.*,java.text.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/styles.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Deposit Money</title>
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
	<%@ include file="header.jsp"%>
	<%@ include file="navbar.jsp"%>
	<div class="main-content">
		<%@ include file="msgContainer.jsp"%>
		<div class="update-form">

			<h3 class="text-center">Deposit Money</h3>
			<form method="post" action="depositMoney">
				<h6>
					Account ID: <input type="text" class="form-control"
						placeholder="Account ID" pattern="[0-9]{9}"
						title="Please enter a 9 digits account id number" name="accountId"
						required>
				</h6>
				<h6>
					Deposit Amount: <input type="text" class="form-control"
						pattern="[0-9]{*}" title="amount should be only in number"
						placeholder="Deposit Amount" name="depositAmount" required>
				</h6>
				<input type="submit" class="btn btn-primary btn-block"
					value="Submit">
			</form>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>