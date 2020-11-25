package com.ilp.bankmgr.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.service.AccountService;

/**
 * Servlet implementation class CreateAccountController
 */
@WebServlet("/CreateAccountController")
public class CreateAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// Base code by Deveshwar, edited by omar
		int customerId = Integer.parseInt(request.getParameter("customerID"));
		int accountId = 0;
		String accountType = request.getParameter("accountType");
		String accountStatus = "Active";
		int accountBalance = Integer.parseInt(request.getParameter("depositAmount"));

		String message = "Account Created Successfully";

		Timestamp date=new Timestamp(System.currentTimeMillis()); //Get current Date

		//public Account(int customerId, int accountId, String accountType, int balance, String message, Date date, String status) {

		Account acct = new Account(customerId,accountId,accountType,accountBalance,message,date,accountStatus);

		boolean status = AccountService.createAccount(acct); //Create DAO

		if (status) { //If account is made and not null

			request.setAttribute("accountId", accountId);
			request.setAttribute("messageType", "success");
			request.setAttribute("messageHeader", "Success");
			request.setAttribute("messageDetail", "Created account successfully..");
			RequestDispatcher rd = request.getRequestDispatcher("createAccount.jsp");
			rd.forward(request, response);

		} else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Invalid Customer ID");
			request.setAttribute("messageDetail", "We couldn't find the Customer ID specified. Please try again.");
			RequestDispatcher rd = request.getRequestDispatcher("createAccount.jsp");
			rd.forward(request, response);
		}
	}

}
