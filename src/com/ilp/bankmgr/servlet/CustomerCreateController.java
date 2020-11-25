package com.ilp.bankmgr.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.bankmgr.bean.Customer;
import com.ilp.bankmgr.service.CustomerService;

/**
 * Servlet implementation class CustomerCreateController
 */
@WebServlet("/createCustomer")
public class CustomerCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerCreateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		if(username==null) {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader","Login Required");
			request.setAttribute("messageDetail", "Please Login before doing anything!!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
		response.sendRedirect("CreateCustomer.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int customerSsnId = Integer.parseInt(request.getParameter("customerSsnID"));
		String customerName = request.getParameter("customerName");
		int age = Integer.parseInt(request.getParameter("customerAge"));
		String address = request.getParameter("customerAddress");
		String city = request.getParameter("customerCity");
		String state = request.getParameter("customerState");
		
		int digits=0;
		int tempssn = customerSsnId;
		while(tempssn!=0) {
			tempssn=tempssn/10;
			digits++;
		}
		
		if(digits==9) {
			
			if(age <1) {
				request.setAttribute("messageType", "error");
				request.setAttribute("messageHeader", "Customer Age Invalid");
				request.setAttribute("messageDetail", "Customer Age can't be " +age);
				request.getRequestDispatcher("CreateCustomer.jsp").forward(request, response);
			}
		
			int customerId = ((customerName+customerSsnId).hashCode());
			if(customerId<0) {
				customerId= customerId*-1;
			}
			int temp = customerId;
			int count =0;
			while(temp>0) {
				temp= temp/10;
				count++;
			}
			while(count>9) {
				customerId=customerId/10;
				count--;
			}
			while(count<9) {
				customerId=customerId*10;
				count++;
			}
			String message ="Customer Created Successfully";
			
			Date date = new Date();
			
			Customer customer = new Customer(customerSsnId, customerId, customerName, age, address, state, city, message, date);
			
			boolean status = CustomerService.createCustomer(customer);
			
			if(status) {
				request.setAttribute("messageType", "success");
				request.setAttribute("messageHeader", "Customer Created Successfuly");
				request.setAttribute("messageDetail", "Customer: "+ customerName +" Created");
				request.getRequestDispatcher("CreateCustomer.jsp").forward(request, response);
				
			}
			else {
				request.setAttribute("messageType", "error");
				request.setAttribute("messageHeader", "Customer Already Exist");
				request.setAttribute("messageDetail", "Customer: "+ customerName +" already exist");
				request.getRequestDispatcher("CreateCustomer.jsp").forward(request, response);
			}
		
		}
		else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Customer ID is not 9 digits");
			request.setAttribute("messageDetail", "Customer ID must be 9 digits");
			request.getRequestDispatcher("CreateCustomer.jsp").forward(request, response);
		}
	}

}
