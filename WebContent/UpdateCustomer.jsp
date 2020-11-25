<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilp.bankmgr.bean.Customer"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/styles.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Update Customer</title>
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
</head>
<body>

	<%@ include file="header.jsp"%>
	<%@ include file="navbar.jsp"%>

	<%
		Customer customer = (Customer) request.getAttribute("customer");
	%>

	<div class="main-content">
		<%@ include file="msgContainer.jsp"%>
		<div class="update-form">

			<form name="form" id="form" method="POST" action="UpdateCustomer2"
				autocomplete="off">
				<h2 class="text-left">Update Customer</h2>

				<!-- Customer SSNID -->
				<div class="form-group">
				<label for="fname">Customer SSN:</label><br>
					<input type="number" class="form-control" name="customerSsnID"
						id="customerSsnID" value=<%=customer.getSsn()%> readonly>
				</div>

				<!-- Customer ID -->
				<div class="form-group">
				<label for="fname">Customer ID:</label><br>
					<input type="number" class="form-control" name="customerID"
						id="customerID" value=<%=customer.getCustomerId()%> readonly>
				</div>

				<!-- Customer Name -->
				<div class="form-group">
				<label for="fname">Customer Name:</label><br>
					<input type="text" class="form-control" name="customerName"
						id="customerName" value=<%=customer.getName()%> readonly>
				</div>

				<!-- New Customer Name -->
				<div class="form-group">
				<label for="fname">Customer New Name:</label><br>
					<input type="text" class="form-control" name="newcustomerName"
						id="newcustomerName" placeholder="New Customer Name"
						required="true">
				</div>

				<!-- Customer Age -->
				<div class="form-group">
				<label for="fname">Customer Age:</label><br>
					<input type="number" class="form-control" name="customerAge"
						id="customerAge" value=<%=customer.getAge()%> readonly>
				</div>

				<!-- New Customer Age -->
				<div class="form-group">
				<label for="fname">Customer New Age:</label><br>
					<input type="number" class="form-control" name="newcustomerAge"
						id="newcustomerAge" placeholder="New Customer Age" required="true">
				</div>

				<!-- Customer Address -->
				<div class="form-group">
				<label for="fname">Customer Address:</label><br>
					<input type="text" class="form-control" name="customerAddress"
						id="customerAddress" value=<%=customer.getAddress()%> readonly>
				</div>

				<!-- New Customer Address -->
				<div class="form-group">
				<label for="fname">Customer New Address:</label><br>
					<input type="text" class="form-control" name="newcustomerAddress"
						id="newcustomerAddress" placeholder="New Customer Address"
						required="true">
				</div>

				<!-- Customer City -->
				<div class="form-group">
				<label for="fname">Customer City:</label><br>
					<input type="text" class="form-control" name="customerCity"
						id="customerCity" value=<%=customer.getCity()%> readonly>
				</div>

				<!-- New Customer City -->
				<div class="form-group">
				<label for="fname">Customer New City:</label><br>
					<input type="text" class="form-control" name="newcustomerCity"
						id="newcustomerCity" placeholder="New Customer City"
						required="true">
				</div>

				<!-- Customer State -->
				<div class="form-group">
				<label for="fname">Customer State:</label><br>
					<input type="text" class="form-control" name="customerState"
						id="customerState" value=<%=customer.getState()%> readonly>
				</div>

				<!-- New Customer State -->
				<div class="form-group">
				<label for="fname">Customer New State:</label><br>
					<input type="text" class="form-control" name="newcustomerState"
						id="newcustomerState" placeholder="Customer State" required="true">
				</div>

				<div>
					<span><input type="submit" value="Submit"></span> <span><input
						type="reset" value="Reset"></span>
				</div>

			</form>

		</div>
	</div>



	<%@ include file="footer.jsp"%>

</body>
</html>