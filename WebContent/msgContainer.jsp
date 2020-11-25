<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message Container</title>
</head>
<body>
	<%
		String messageType = (String) request.getAttribute("messageType");
		String header = (String) request.getAttribute("messageHeader");
		String details = (String) request.getAttribute("messageDetail");
		if (header != null) {
	%>

	<div class="content-wrapper">
		<div class="xy-${messageType}">
			<h4><%=header %></h4>
			<p> <%=details %></p>
		</div>
	</div>
<%}
	
	request.setAttribute("messageType",null);
	request.setAttribute("messageDetail",null);
	request.setAttribute("messageHeader",null);
%>
</body>
</html>