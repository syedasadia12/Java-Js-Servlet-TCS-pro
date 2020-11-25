<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/styles.css">
<script type="text/javascript" src="js/example.js"></script>
<title>Delete Customer</title>
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
	<%@ include file="header.jsp"%>
	<%@ include file="navbar.jsp"%>
	<div class="main-content">
		<%@ include file="msgContainer.jsp"%>
		<div class="content-wrapper">

			<div class="xy-form-wrapper">

				<form class="xy-form" action="findCustomer" method="post">
					<h2 class="form-head">Find Customer Accounts</h2>
					<div class="form-field-container">


						<div class="form-row">
							<div class="form-col" style="float: left">
								<label for="ssn">By SSN</label> <input type="radio"
									name="searchType" value="ssn" onClick="changePlaceholder(this)"
									checked>
							</div>
							<div class="form-col" style="float: right">
								<label for="custId">By Customer Id</label> <input type="radio"
									name="searchType" value="custId"
									onClick="changePlaceholder(this)">
							</div>
						</div>
						<div class="form-row">
							<input type="text" name="ssn" id="ssn"
								placeholder=" Input Your SSN here ..." pattern="[0-9]{9}"
								title="9 digits social security number" required>
						</div>
						<div class="form-row">
							<input type="submit" value="Submit">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>