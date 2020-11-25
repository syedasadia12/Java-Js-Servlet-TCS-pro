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
import com.ilp.bankmgr.service.AccountService;

/**
 * Servlet implementation class findAccount
 */
@WebServlet("/findAccount")
public class findAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("deleteAccount.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid = request.getParameter("custId");
		String ssn = request.getParameter("ssn");

		List<Account> accounts = new ArrayList<>();
		
		if(cid!=null){
			
			accounts = AccountService.searchAcountByCustomerId(Integer.parseInt(cid));
			
			if(accounts.size()!=0) {
				request.setAttribute("accounts", accounts);
				request.getRequestDispatcher("deleteAccount.jsp").forward(request,response);
			}else {
				request.setAttribute("messageType","error"); // error, success, info
				request.setAttribute("messageHeader", "Find Customer Fail");
				request.setAttribute("messageDetail", "There is not such customer with "+(cid!=null?"id: "+cid:"ssn: "+ssn)+". Or there is no account associate with "+(cid!=null?"id: "+cid:"ssn: "+ssn));
				request.getRequestDispatcher("deleteAccount.jsp").forward(request,response);
			}
			
		}else {
			accounts = AccountService.searchAcountByCustomerSSN(Integer.parseInt(ssn));
			
			if(accounts.size()!=0) {
				request.setAttribute("accounts", accounts);
				request.getRequestDispatcher("deleteAccount.jsp").forward(request,response);
			}else {
				request.setAttribute("messageType","error"); // error, success, info
				request.setAttribute("messageHeader", "Find Customer Fail");
				request.setAttribute("messageDetail", "There is not such customer with "+(cid!=null?"id: "+cid:"ssn: "+ssn)+". Or there is no account associate with "+(cid!=null?"id: "+cid:"ssn: "+ssn));
				request.getRequestDispatcher("deleteAccount.jsp").forward(request,response);

			}
		}
	}

}
