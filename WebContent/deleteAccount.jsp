<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ilp.bankmgr.bean.Account"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Account</title>
<link rel="stylesheet" href="styles/styles.css">
<script type="text/javascript" src="js/example.js"></script>
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

	<%
		List<Account> accounts = (ArrayList<Account>) request.getAttribute("accounts");
	%>


	<div class="main-content">
		<%@ include file="msgContainer.jsp"%>
		<div class="content-wrapper">

			<div class="xy-form-wrapper">

				<%
					if (accounts == null) {
				%>
				<%@ include file="searchCustomerEmb.jsp"%>
				<%
					} else {
				%>
				<form class="xy-form" action="deleteAccount" method="post">
					<div class="form-head">
						<h2>Delete Account</h2>
					</div>
					<div class="form-field-container">
						<div class="form-row">
							<%
								String ssn = (String) request.getParameter("ssn");
							String cid = (String) request.getParameter("custId");
							if (cid != null) {
							%>

							<label>Customer ID: <%=cid%> <a href="deleteAccount">edit</a></label>
							<%
								} else {
							%>
							<label>Customer SSN: <%=ssn%> <a href="deleteAccount">edit</a>
							</label>
							<%
								}
							%>
						</div>
						<div class="form-row">
							<label>Account ID: </label> <select id="acountSelect"
								onChange='changeType(this);'>
								<%
									for (int i = 0; i < accounts.size(); i++) {
									Account account = accounts.get(i);
								%>

								<option
									value='{"accountId":<%=account.getAccountId()%>, "accountType":"<%=account.getAccountType()%>"}'>
									<%=account.getAccountId()%></option>

								<%
									}
								%>

							</select>
						</div>
						<div class="form-row">
							<div class="form-col">
								<label>Account Type: </label>
							</div>
							<div class="form-col">
								<input type="text" id="accType"
									value="<%=accounts.get(0).getAccountType()%>" disabled>
							</div>
						</div>
						<input type="hidden" id="xy_hidden" name="accountId"
							value="<%=accounts.get(0).getAccountId()%>">
						<div class="form-row">
							<input type="submit" value="Confirm Delete">
						</div>
					</div>
				</form>
				<%
					}
				%>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>