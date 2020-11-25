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
 * Servlet implementation class deleteAccountServlet
 */
@WebServlet("/deleteAccount")
public class deleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		
		String cid = request.getParameter("custId");
		String ssn = request.getParameter("ssn");
		
		
		
		if(cid==null && ssn==null) {
			response.sendRedirect("deleteAccount.jsp");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accountId = request.getParameter("accountId");
		boolean success = false;
		AccountService acsv = new AccountService();
		success = acsv.deleteAccountById(Integer.parseInt(accountId));
		
		if(success) {
			request.setAttribute("messageType", "success");
			request.setAttribute("messageHeader", "Deletion Success");
			request.setAttribute("messageDetail", "Account: "+accountId+" Deleted");
			request.getRequestDispatcher("deleteAccount.jsp").forward(request, response);
		}else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Deletion Fail");
			request.setAttribute("messageDetail", "Account: "+accountId+" fail to delete. This issue may causes by no acccount found in database. Please check your account status for further details ");
			request.getRequestDispatcher("deleteAccount.jsp").forward(request, response);
			
		}
	}

}
