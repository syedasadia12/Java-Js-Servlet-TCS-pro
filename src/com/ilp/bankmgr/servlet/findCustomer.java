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
import com.ilp.bankmgr.service.CustomerService;

/**
 * Servlet implementation class findCustomer
 */
@WebServlet("/findCustomer")
public class findCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("deleteCustomer.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid = request.getParameter("custId");
		String ssn = request.getParameter("ssn");
		Customer cust = null;
		
		if(cid!=null) {
			cust = CustomerService.searchCustomerById(Integer.parseInt(cid));
			
		}else {
			cust = CustomerService.searchCustomerBySsnId(Integer.parseInt(ssn));	
		}
		if(cust==null) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Delete Customer Fail");
			request.setAttribute("messageDetail", "Customer not found. Please check your customer id");
			request.getRequestDispatcher("deleteCustomer.jsp").forward(request, response);
		}else {
			request.setAttribute("cust", cust);
			request.getRequestDispatcher("customerDetails.jsp").forward(request, response);
		}
	}

}
