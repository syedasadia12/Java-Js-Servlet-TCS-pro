<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="styles/styles.css">
<title>Login Page</title>
<style>
.login-form {
	width: 350px;
	margin: 0 auto;
	font-size: 20px;
}

.login-form form {
	margin-bottom: 14px;
	background: #f7f7f7;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.login-form h2 {
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
</style>
</head>
<body>

	<%@ include file="header.jsp"%>
	
	<div class="main-content">
	<%@ include file="msgContainer.jsp" %>
		<div class="login-form">
			<form action="loginServlet" method="post">
				<h2 class="text-left">Login</h2>
				<div class="form-group">
					<input type="text" class="form-control" name="username" placeholder="Username"
						required="required">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="password" placeholder="Password"
						required="required">
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block">Login</button>
				</div>
			</form>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>