package com.ilp.bankmgr.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.bean.Transaction;
import com.ilp.bankmgr.service.AccountService;
import com.ilp.bankmgr.service.TransactionService;

/**
 * Servlet implementation class TransferServlet
 */
@WebServlet("/transferMoney")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("Transfer.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int accountId = Integer.parseInt(request.getParameter("accountId"));
		int targetAccountId = Integer.parseInt(request.getParameter("targetAccountId"));
		int transferAmount = Integer.parseInt(request.getParameter("transferAmount"));

		int transactionId = 0;
		String transactionType = "TRANSFER";
		Timestamp date = new Timestamp(System.currentTimeMillis());

		Account acc = AccountService.searchAccountByAccountId(accountId);

		if (acc == null) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Transfer Fail");
			request.setAttribute("messageDetail", "Source account not found. Please check your account id");

			request.getRequestDispatcher("Transfer.jsp").forward(request, response);
		}

		Account acc1 = AccountService.searchAccountByAccountId(targetAccountId);

		if (acc1 == null) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Transfer Fail");
			request.setAttribute("messageDetail", "Target account not found. Please check your account id");

			request.getRequestDispatcher("Transfer.jsp").forward(request, response);
		}

		if (acc.getBalance() - transferAmount < 0) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Transfer Fail");
			request.setAttribute("messageDetail", "Insufficient Funds in account " + acc.getAccountId());
			request.getRequestDispatcher("Transfer.jsp").forward(request, response);
		}

		int customerId = acc.getCustomerId();

		Transaction transaction = new Transaction(transactionId, accountId, transactionType, transferAmount, customerId,
				targetAccountId, date);

		boolean success = TransactionService.transfer(transaction);

		if (success) {

			request.setAttribute("messageType", "success");
			request.setAttribute("messageHeader", "Transfer Successful");
			request.setAttribute("messageDetail", "Source Balance: $" + (acc.getBalance() - transferAmount)
					+ "  <br>  Target Balance: $" + (acc1.getBalance() + transferAmount));

			request.getRequestDispatcher("Transfer.jsp").forward(request, response);
		} else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Transfer Fail");
			request.setAttribute("messageDetail", "Account not found. Please check your account id");
			request.getRequestDispatcher("Transfer.jsp").forward(request, response);
		}
	}

	// doGet(request, response);

}
