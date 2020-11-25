package com.ilp.bankmgr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.service.AccountStatusService;

/**
 * Servlet implementation class ViewAllAccountStatus
 */
@WebServlet("/accountStatus")
public class ViewAllAccountStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllAccountStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			ArrayList<Account> acc = AccountStatusService.searchAllAccounts();
			HttpSession session = request.getSession();
		/*
		 * for(Account myacc : acc ) { System.out.println(myacc.getCustomerId());
		 * System.out.println(myacc.getAccountId());
		 * System.out.println(myacc.getAccountType());
		 * System.out.println(myacc.getMessage());
		 * System.out.println(myacc.getStatus()); System.out.println(myacc.getDate());
		 * System.out.println(myacc.getBalance());
		 * 
		 * }
		 */
			session.setAttribute("Account_details", acc);
			
			response.sendRedirect("AccountStatus.jsp");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			}

}
