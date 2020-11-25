package com.ilp.bankmgr.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilp.bankmgr.bean.Customer;
import com.ilp.bankmgr.service.CustomerService;

/**
 * Servlet implementation class CustomerUpdateController
 */
@WebServlet("/UpdateCustomer")
public class CustomerUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.sendRedirect("UpdateCustomerSearch.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Customer a =null;
		if(request.getParameter("customerSsnID") != "") {
			int customerSsnId = Integer.parseInt(request.getParameter("customerSsnID"));
			a = CustomerService.searchCustomerBySsnId(customerSsnId);
		}
		else if(request.getParameter("customerID") != "") {
			int customerID = Integer.parseInt(request.getParameter("customerID"));
			a = CustomerService.searchCustomerById(customerID);
		}
		
		else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Enter One of the fields");
			request.setAttribute("messageDetail", "Both fields can't be blank");
			request.getRequestDispatcher("UpdateCustomerSearch.jsp").forward(request, response);
		}
		
		if(a!=null) {
			request.setAttribute("customer", a);
			RequestDispatcher rd = request.getRequestDispatcher("UpdateCustomer.jsp");
			
			rd.forward(request, response);
		}
		else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Invalid Customer");
			request.setAttribute("messageDetail", "Customer Does Not Exist");
			request.getRequestDispatcher("UpdateCustomerSearch.jsp").forward(request, response);
		}
		
		
	}

}
