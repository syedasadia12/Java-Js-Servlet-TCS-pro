package com.ilp.bankmgr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.bankmgr.bean.Transaction;
import com.ilp.bankmgr.service.TransactionService;

/**
 * Servlet implementation class PrintStatementController
 */
@WebServlet("/getStatement")
public class PrintStatementController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrintStatementController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("getStatement.jsp");
	}
	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		List<Transaction> result = new ArrayList<Transaction>();

		switch (request.getParameter("transOrDate")) {
		case "trans":
			int numOfTrans = Integer.parseInt(request.getParameter("Trans"));
			result = TransactionService.getTransactions(accountID, numOfTrans); // Create DAO
			break;
		case "date":
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date tempDate = sdf.parse(request.getParameter("startDate"));
				Date startDate = new Date(tempDate.getTime());
				tempDate = sdf.parse(request.getParameter("endDate"));
				Date endDate = new Date(tempDate.getTime());

				result = TransactionService.getTransactions(accountID, startDate, endDate); // Create DAO
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default: // Error state
			break;
		}
		HttpSession session = request.getSession();
		session.setAttribute("transList", result);
		request.setAttribute("transList", result);
		request.setAttribute("accountId", accountID);
		if(result==null || result.size()==0) {
			request.setAttribute("messageType", "info");
			request.setAttribute("messageHeader", "No Records Found");
			request.setAttribute("messageDetail", "Please enter different account Id or filter criteria");
			request.getRequestDispatcher("getStatement.jsp").forward(request, response);
		}
		request.getRequestDispatcher("printResult2.jsp").forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
