<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Customer</title>
</head>
<body>
	<form class="xy-form" action="findAccount" method="post">
	<h2 class="form-head">Find Customer Accounts</h2>
		<div class="form-field-container">
			

			<div class="form-row">
				<div class="form-col" style="float:left">
					<label for="ssn">By SSN</label> <input type="radio"
						name="searchType" value="ssn" onClick="changePlaceholder(this)"
						checked>
				</div>
				<div class="form-col" style="float:right">
					<label for="custId">By Customer Id</label> <input type="radio"
						name="searchType" value="custId" onClick="changePlaceholder(this)">
				</div>
			</div>
			<div class="form-row">
				<input type="text" name="ssn" id="ssn"
					placeholder=" Input Your SSN here ..." pattern="[0-9]{9}" title="9 digits social security number" required>
			</div>
			<div class="form-row">
				<input type="submit" value="Submit">
			</div>
		</div>
	</form>
</body>
</html>