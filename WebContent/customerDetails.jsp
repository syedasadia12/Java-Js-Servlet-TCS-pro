<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilp.bankmgr.bean.Customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Customer</title>
<link rel="stylesheet" href="styles/styles.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script type="text/javascript" src="js/example.js"></script>
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
		String sensitiveRole = "Customer_Account_Executive";
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
	
	<% Customer cust = (Customer)request.getAttribute("cust");  

	%>
	
	<%@ include file="header.jsp"%>
	<%@ include file="navbar.jsp"%>
	<div class="main-content">
		<%@ include file="msgContainer.jsp"%>
		<div class="update-form">

			<h3 class="text-center">Delete Customer</h3>
			<form method="post" action="deleteCustomer">
							<div class="form-group">
					<h6>SSN: </h6><input type="number" class="form-control" name="customerSsnID"
						id="customerSsnID" value=<%=cust.getSsn()%> readonly>
				</div>

				<!-- Customer ID -->
				<div class="form-group">
					<h6>Customer Id: </h6><input type="number" class="form-control" name="customerID"
						id="customerID" value=<%=cust.getCustomerId()%> readonly>
				</div>

				<!-- Customer Name -->
				<div class="form-group">
					<h6>Customer Name: </h6><input type="text" class="form-control" name="customerName"
						id="customerName" value=<%=cust.getName()%> readonly>
				</div>

				<!-- Customer Age -->
				<div class="form-group">
					<h6>Customer Age: </h6><input type="number" class="form-control" name="customerAge"
						id="customerAge" value=<%=cust.getAge()%> readonly>
				</div>
			
				<input type="submit" class="btn btn-primary btn-block"
					value="Submit">
			</form>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>