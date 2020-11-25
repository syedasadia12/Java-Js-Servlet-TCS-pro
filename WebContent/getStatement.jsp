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
<title>Account Statement</title>
<style>
body {
	background-color: #e6e6fa;
}

.statement-form {
	width: 400px;
	margin: 0 auto;
	font-size: 20px;
}

.statement-form form {
	
	background: #f7f7f7;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.statement-form h2 {
	margin: 0 0 15px;
}

.form-control, .btn {
	min-height: 38px;
	border-radius: 2px;
}

.radio {
	font-size: 15px;
	margin-left: 20px;
}

.radio-options {
	font-size: 20px;
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
		<%@include file="msgContainer.jsp"%>
		<div class="statement-form">
			<form action="getStatement" name="form" id="form" method="POST"
				autocomplete="off">
				<h2 class="text-left">Account Statement</h2>

				<!-- Account ID -->
				<div class="form-group">
					<input type="number" class="form-control" step="1" name="accountID"
						id="accountID" placeholder="Account ID" required="true">
				</div>

				<!-- Pick between last n transactions or between date range -->
				<div class="radio form-group">
					<input type="radio" onclick="revealDiv()" name="transOrDate"
						id="transBt" value="trans" checked="checked"> Last # of
					Transactions<br> <input type="radio" onclick="revealDiv()"
						name="transOrDate" id="dateBt" value="date"> Within Date
					Range
				</div>

				<div id="transDiv" class="radio-options form-group">
					<!-- Last N Transactions (option to choose from drop down 1-10) -->
					Number of Transactions (1-10): <input type="number"
						class="form-control" name="Trans" id="Trans" step="1" min="1"
						max="10" value="1">
				</div>
				<!-- or Start date and End date -->
				<div id="dateDiv" class="radio-options form-group"
					style="display: none">
					Start Date (YYYY-MM-DD) <input type="date" class="form-control"
						name="startDate" id="startDate" value="2000-10-09"
						min="1970-01-01" max="2099-12-31"> End Date (YYYY-MM-DD) <input
						type="date" class="form-control" name="endDate" id="endDate"
						value="2020-10-09" min="1970-01-01" max="2099-12-31">
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
		//var transDiv = document.getElementById("transDiv");
		//var dateDiv = document.getElementById("dateDiv");
		//transDiv.style.display = "block";
		//dateDiv.style.display = "none";

		function revealDiv() { //Decide whether or not to use # of transactions or date range
			//Get TransOrDate
			var transBt = document.getElementById("transBt");
			var dateBt = document.getElementById("dateBt");
			//Get Divs
			var transDiv = document.getElementById("transDiv");
			var dateDiv = document.getElementById("dateDiv");

			//Reveal appropriate option
			if (transBt.checked == true && dateBt.checked == false) {
				//Trans
				transDiv.style.display = "block";
				//Date`
				dateDiv.style.display = "none";
			} else if (transBt.checked == false && dateBt.checked == true) {
				//Trans
				transDiv.style.display = "none";
				//Date
				dateDiv.style.display = "block";
			} else { //Error state
				//Trans
				transDiv.style.display = "none";
				//Date
				dateDiv.style.display = "none";
			}
		}

		function Validate() {
			//Get Divs
			var acctId = document.getElementById("accountID");
			//Get Submit button
			var submit = document.getElementById("submit");

			if (acctId.value > 0) {
				submit.disabled = false;
			} else {
				submit.disabled = true;
			}
		}

		window.setInterval(Validate, 50);
	</script>
</body>
</html>