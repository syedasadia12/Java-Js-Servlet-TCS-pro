package com.ilp.bankmgr.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class DepositServlet
 */
@WebServlet("/withdrawMoney")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("withdraw.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub

		int accountId = Integer.parseInt(request.getParameter("accountId"));
		int withdrawAmount = (Integer.parseInt(request.getParameter("withdrawAmount")));

		
		int transactionId = 0;
		String transactionType = "WITHDRAW";
		int targetAccountId = Integer.parseInt(request.getParameter("accountId"));
		Timestamp date=new Timestamp(System.currentTimeMillis());
		
		Account acc=AccountService.searchAccountByAccountId(accountId);
		if(acc==null) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Withdraw Fail");
			request.setAttribute("messageDetail", "Account not found. Please check your account id");
			
			request.getRequestDispatcher("withdraw.jsp").forward(request, response);	
		}
		if(acc.getBalance()-withdrawAmount<0) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Withdraw Fail");
			request.setAttribute("messageDetail", "Insufficient Funds");
			
			request.getRequestDispatcher("withdraw.jsp").forward(request, response);
		}
		int customerId = acc.getCustomerId();
		
		Transaction t = new Transaction(transactionId, accountId, transactionType, withdrawAmount, customerId, targetAccountId, date);
		
		boolean success = TransactionService.withdraw(t);
		if (success) {

			request.setAttribute("messageType", "success");
			request.setAttribute("messageHeader", "Withdraw Successful");
			request.setAttribute("messageDetail", "Current Balance: $" + (acc.getBalance() - withdrawAmount));

			request.getRequestDispatcher("withdraw.jsp").forward(request, response);
		} else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Withdraw Fail");
			request.setAttribute("messageDetail", "Account not found. Please check your account id");
			request.getRequestDispatcher("withdraw.jsp").forward(request, response);
		}
	}
}
