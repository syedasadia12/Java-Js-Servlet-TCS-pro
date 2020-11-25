package com.ilp.bankmgr.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.bankmgr.bean.User;
import com.ilp.bankmgr.service.UserService;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService usvs = new UserService();
		
		User user = usvs.login(username,password);
		HttpSession session = null;
		if(user!=null) {
			session = request.getSession(true);
			session.setAttribute("username", username);
			session.setAttribute("empName", user.getEmpName());
			session.setAttribute("userType", user.getUserType());
			session.setAttribute("lastLogin", user.getLastLogin());
			
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else {
			request.setAttribute("messageType", "error");
			request.setAttribute("messageHeader", "Login Fail");
			request.setAttribute("messageDetail", "Wrong password or Username! Please try again or contact your account manager");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
	}

}
