<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- Added import statements -->
<%@ page import="com.ilp.bankmgr.bean.*"%>
<%@ page import="com.ilp.bankmgr.dao.*"%>
<%@ page import="com.ilp.bankmgr.service.*"%>
<%@ page import="com.ilp.bankmgr.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.*,java.text.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="styles/styles.css">
<title>Account Statement</title>

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
</head>
<body>

	<%@ include file="header.jsp"%>
	<%@ include file="navbar.jsp"%>

	<div class="main-content">
		<%@ include file="msgContainer.jsp"%>
		<div class="content-wrapper">
			<span id=printable>
				<div>
					<h2 class="text-center">Account Statement</h2>
				</div> <%
 	ArrayList<Transaction> transList = (ArrayList<Transaction>) request.getAttribute("transList");
 %>

				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th>Transaction ID</th>
							<th>Description</th>
							<th>Date (YYYY-MM-DD)</th>
							<th>Amount</th>
						</tr>
					</thead>
					<%
						if (transList.isEmpty()) {
					%>

					<h3>No Records found!</h3>
					<h4>Refresh the page for updated records.</h4>
					<%
						} else {
					%>
					<%
					int i=0;
						for (Transaction t : transList) {
							i++;
					%>

					<tr>
						<td><%=t.getTransactionId()+i%></td>
						<td><%=t.getTransactionType()%></td>
						<td>
							<%
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
							%> <%=sdf.format(t.getDate())%>
						</td>
						<td><%=t.getAmount()%></td>
					</tr>
					<%
						}
					%>
					<%
						}
					%>
				</table>
			</span>
			<div class="text-center">
				<button type="button" class="btn btn-primary" onclick="window.location.reload();">Refresh the List</button>
				<br>
				<button type="button" id="pdf" class="btn " href="#" onClick="javascript:ToPDFFile();">Download PDF</button>
				<br>
				<form action="ExcelStatementController" method="POST">
					<button type="submit" class=" btn " id="pdf">Download Excel (.csv)</button>
				</form>
			</div>
		</div>

	</div>

	<%@ include file="footer.jsp"%>
	<script>
		function ToPDFFile() {
			var toPDF = document.getElementById('printable');
			var newOpenWindow = window.open('', '_blank',
					'width=700,height=300');
			newOpenWindow.document.open();
			newOpenWindow.document.write('<html><body onload="window.print()">'
					+ toPDF.innerHTML + '</body></html>');
			newOpenWindow.document.close();
		}
	</script>
</body>
</html>