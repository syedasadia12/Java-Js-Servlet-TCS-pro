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
 * Servlet implementation class CustomerUpdate2Controller
 */
@WebServlet("/UpdateCustomer2")
public class CustomerUpdate2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdate2Controller() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("customerID"));
		String customerName = request.getParameter("newcustomerName");
		int age = Integer.parseInt(request.getParameter("newcustomerAge"));
		String address = request.getParameter("newcustomerAddress");
		String city = request.getParameter("newcustomerCity");
		String state = request.getParameter("newcustomerState");
		
		Customer a = CustomerService.searchCustomerById(customerId);
		a.setName(customerName);
		a.setAge(age);
		a.setAddress(address);
		a.setCity(city);
		a.setState(state);
		
		if(age <1) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Customer Age Invalid");
			request.setAttribute("messageDetail", "Customer Age can't be " +age);
			request.getRequestDispatcher("UpdateCustomerSearch.jsp").forward(request, response);
		}
		
		boolean status = CustomerService.updateCustomer(a);
		
		if(status) {
			
			request.setAttribute("customerId", customerId);
			request.setAttribute("messageType", "success");
			request.setAttribute("messageHeader", "Customer Updated");
			request.setAttribute("messageDetail", "Customer: " + customerName + " was updated");
			request.getRequestDispatcher("UpdateCustomerSearch.jsp").forward(request, response);
			
			
		}
		else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Invalid Customer");
			request.setAttribute("messageDetail", "Customer Does Not Exist");
			request.getRequestDispatcher("UpdateCustomerSearch.jsp").forward(request, response);
		}
		
	}

}
