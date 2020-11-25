<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Navigation Bar</title>
</head>
<body>
<div class="header">
<div class="content-wrapper"><nav>
				<ul class="nav-bar">
					<li class="nav-item"><a href="home.jsp">Home</a></li>
					<li class="nav-item">Customer Management
						<div class="drop-down-item">
							<a href="createCustomer">Create Customer</a>
							<a href="deleteCustomer">Delete Customer</a>
							<a href="UpdateCustomer">Update Customer</a>
						</div>

					</li>
					<li class="nav-item">Account Management
						<div class="drop-down-item">
							<a href="createAccount.jsp">Create Account</a>
							<a href="deleteAccount">Delete Account</a>
							
						</div>
					</li>
					<li class="nav-item">Status Details
						<div class="drop-down-item">
							<a href="customerStatus">View Customer Status</a>
							<a href="accountStatus">View Account Status</a>
						</div>
					</li>
					<li class="nav-item">Account Operation
						<div class="drop-down-item">
							<a href="searchCustomer">Search Customer</a>
							<a href="searchAccount">Search Account</a>
							<a href="getStatement">Get Account Statement</a>
							<a href="depositMoney">Deposit Money</a>
							<a href="withdrawMoney">Withdraw Money</a>
							<a href="transferMoney">Transfer Money</a>	
						</div>
					</li>
					<li class="nav-item"><a href="logout">Logout</a></li>

				</ul>
			</nav></div></div>
</body>
</html>