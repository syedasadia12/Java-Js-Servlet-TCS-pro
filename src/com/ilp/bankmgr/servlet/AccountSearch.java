package com.ilp.bankmgr.servlet;

import java.io.IOException;
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
import com.ilp.bankmgr.service.AccountService;
import com.ilp.bankmgr.service.AccountStatusService;

/**
 * Servlet implementation class CustomerSearch
 */
@WebServlet("/searchAccount")
public class AccountSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Account> acc=new ArrayList<Account>();
		if(request.getParameter("caccid")!="")
		{
			Account acc1=AccountService.searchAccountByAccountId(Integer.parseInt(request.getParameter("caccid")));
			if(acc1!=null)
				{
				acc.add(acc1);
				request.setAttribute("account", acc);
				}
		}
		else if(request.getParameter("cid")!="")
		{
			acc=AccountService.searchAcountByCustomerId(Integer.parseInt(request.getParameter("cid")));
			request.setAttribute("account", acc);
			
		}
		
		if( acc.isEmpty()==false)
		{
			RequestDispatcher rd=request.getRequestDispatcher("accountSearchResults.jsp");
			rd.forward(request,response);
			response.sendRedirect("accountSearchResults.jsp");
		}
		else
		{
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Invalid Account");
			request.setAttribute("messageDetail", "Account Does not Exist");
			request.getRequestDispatcher("accountSearch.jsp").forward(request, response);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("accountSearch.jsp");
	}

}
