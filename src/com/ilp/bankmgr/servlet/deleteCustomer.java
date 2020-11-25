package com.ilp.bankmgr.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.bean.Customer;
import com.ilp.bankmgr.service.AccountService;
import com.ilp.bankmgr.service.CustomerService;

/**
 * Servlet implementation class deleteCustomer
 */
@WebServlet("/deleteCustomer")
public class deleteCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteCustomer() {
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
		response.sendRedirect("deleteCustomer.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String ssn = request.getParameter("customerSsnID");
		Customer cust = CustomerService.searchCustomerBySsnId(Integer.parseInt(ssn));
		System.out.println(cust.getCustomerId());
		List<Account> acls = new ArrayList<Account>();
		AccountService asvs = new AccountService();
		if (cust == null) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Delete Customer Fail");
			request.setAttribute("messageDetail", "Customer not found. Please check your customer id");
			request.getRequestDispatcher("deleteCustomer.jsp").forward(request, response);
		} else {
			acls = asvs.searchAcountByCustomerId(cust.getCustomerId());
		}
		boolean success = true;
		for (Account c : acls) {
			if (!asvs.deleteAccountById(c.getAccountId())) {
				request.setAttribute("messageType", "error");
				request.setAttribute("messageHeader", "Delete Customer Fail");
				request.setAttribute("messageDetail", "Internal Error, there is something wrong with database");
				request.getRequestDispatcher("deleteCustomer.jsp").forward(request, response);
			}
		}
		success = CustomerService.deleteCustomer(cust);

		if (!success) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Delete Customer Fail");
			request.setAttribute("messageDetail", "Internal Error, there is something wrong with database");
			request.getRequestDispatcher("deleteCustomer.jsp").forward(request, response);
		} else {
			request.setAttribute("messageType", "success");
			request.setAttribute("messageHeader", "Delete Customer Successful");
			request.setAttribute("messageDetail", "Customer " + cust.getName() + " Deleted");
			request.getRequestDispatcher("deleteCustomer.jsp").forward(request, response);
		}

	}
}