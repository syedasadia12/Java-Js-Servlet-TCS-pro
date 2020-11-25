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
<title>Create Account</title>

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

<style>
body {
	background-color: #e6e6fa;
}

.account-form {
	width: 350px;
	margin: 0 auto;
	font-size: 20px;
}

.account-form form {
	background: #f7f7f7;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.account-form h2 {
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

	<%@ include file="header.jsp"%>
	<%@ include file="navbar.jsp"%>
	<div class="main-content">
		<%@ include file="msgContainer.jsp"%>


		<div class="account-form">

			<form name="form" id="form" method="POST"
				action="CreateAccountController" autocomplete="off">
				<h2 class="text-left">Create Account</h2>

				<!-- Customer ID -->
				<div class="form-group">
					<input type="number" class="form-control" step="1"
						name="customerID" id="customerID" placeholder="Customer ID"
						required="true">
				</div>

				<!-- Drop Down - Savings/Current Account -->
				<div class="form-group">
					Account Type: <select class="form-control" name=accountType
						id=accountType value="none" required="true">
						<option value="none">Please Select...</option>
						<option value="Savings">Savings</option>
						<option value="Checking">Checking</option>
					</select>
				</div>

				<!-- Integer Deposit Amount -->
				<div class="form-group">
					Deposit Amount: $ <input type="number" class="form-control"
						step="1" min=0 name="depositAmount" id="depositAmount"
						autocomplete="on" placeholder="Enter amount">
				</div>
				<!-- Display confirmation or error message with button press-->
				<div class="form-group">
					<input type="submit" class="btn btn-primary btn-block" id="submit"
						disabled="true">
				</div>
				<span id="message" class="warning">(*) Fields are Mandatory</span>
			</form>
			
		</div>
	</div>

	<%@ include file="footer.jsp"%>

	<script>
		function Validate() {
			//Page Variables to check
			var submit = document.getElementById("submit");
			//Cannot check CustomerID (need database)
			var accountType = document.getElementById("accountType");
			var depositAmount = document.getElementById("depositAmount");

			//Error message
			//var message = document.getElementById("message");

			if (accountType.value == "none") {
				//message.innerText = "Error: Invalid Account Type. Please select an account type.";
				submit.disabled = true;
			} else if (depositAmount.value <= 0) {
				//message.innerText = "Error: Invalid Deposit amount. Please deposit at least $1.";
				submit.disabled = true;
			}
			//Submit if no preventable errors
			else {
				//message.innerText = "";
				submit.disabled = false;
			}
		}
		window.setInterval(Validate, 50);
	</script>


</body>
</html>