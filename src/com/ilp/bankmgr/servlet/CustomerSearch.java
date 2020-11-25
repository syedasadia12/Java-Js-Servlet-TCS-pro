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
 * Servlet implementation class CustomerSearch
 */
@WebServlet("/searchCustomer")
public class CustomerSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("customerSearch.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer a = null;
		if(request.getParameter("cssn") != "") {
			int customerSsnId = Integer.parseInt(request.getParameter("cssn"));
			a = CustomerService.searchCustomerBySsnId(customerSsnId);
		}
		else if (request.getParameter("cid")!="") {
			int customerID = Integer.parseInt(request.getParameter("cid"));
			a = CustomerService.searchCustomerById(customerID);
		}
		
		if(a!=null) {
			request.setAttribute("customer", a);
			RequestDispatcher rd = request.getRequestDispatcher("customerSearchResults.jsp");
			
			rd.forward(request, response);
		}
		else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Invalid Customer");
			request.setAttribute("messageDetail", "Customer Does Not Exist");
			request.getRequestDispatcher("customerSearch.jsp").forward(request, response);
		}
	}

}
