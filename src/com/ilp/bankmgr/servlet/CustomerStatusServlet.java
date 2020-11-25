package com.ilp.bankmgr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.bean.Customer;
import com.ilp.bankmgr.service.AccountStatusService;
import com.ilp.bankmgr.service.CustomerStatusService;

/**
 * Servlet implementation class CustomerStatusServlet
 */
@WebServlet("/customerStatus")
public class CustomerStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerStatusServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Customer> cust = CustomerStatusService.searchCustomer();

		for (Customer mycust : cust) {
			System.out.println(mycust.getCustomerId());
			System.out.println(mycust.getName());
			System.out.println(mycust.getSsn());
			System.out.println(mycust.getAge());
			System.out.println(mycust.getDate());
			System.out.println(mycust.getCity());

		}

		request.setAttribute("cust", cust);

		request.getRequestDispatcher("CustomerStatus.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
