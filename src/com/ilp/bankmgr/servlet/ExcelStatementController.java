package com.ilp.bankmgr.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.bankmgr.bean.Transaction;
import com.ilp.bankmgr.service.TransactionService;

/**
 * Servlet implementation class PDFsStatementController
 */
@WebServlet("/ExcelStatementController")
public class ExcelStatementController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExcelStatementController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		 * int accountID = Integer.parseInt(request.getParameter("accountID"));
		 * List<Transaction> result = new ArrayList<Transaction>();
		 * 
		 * switch (request.getParameter("transOrDate")) { case "trans": int numOfTrans =
		 * Integer.parseInt(request.getParameter("Trans")); result =
		 * TransactionService.getTransactions(accountID, numOfTrans); // Create DAO
		 * break; case "date": try { SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy-MM-dd"); java.util.Date tempDate =
		 * sdf.parse(request.getParameter("startDate")); Date startDate = new
		 * Date(tempDate.getTime()); tempDate =
		 * sdf.parse(request.getParameter("endDate")); Date endDate = new
		 * Date(tempDate.getTime());
		 * 
		 * result = TransactionService.getTransactions(accountID, startDate, endDate);
		 * // Create DAO } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } break; default: // Error state break; }
		 */
		HttpSession session = request.getSession();
		ArrayList<Transaction> list = (ArrayList<Transaction>) session.getAttribute("transList");

		// Print PDF
		/*
		 * String pdfFileName = "pdf-test.pdf"; String contextPath =
		 * getServletContext().getRealPath(File.separator); File pdfFile = new
		 * File(contextPath + pdfFileName);
		 * 
		 * response.setContentType("application/pdf");
		 * response.addHeader("Content-Disposition", "attachment; filename=" +
		 * pdfFileName); response.setContentLength((int) pdfFile.length());
		 * 
		 * FileInputStream fileInputStream = new FileInputStream(pdfFile); OutputStream
		 * responseOutputStream = response.getOutputStream(); int bytes; while ((bytes =
		 * fileInputStream.read()) != -1) { responseOutputStream.write("Testing pdf"); }
		 */

		//Print Excel sheet
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"Account_Statement_"+LocalDateTime.now()+".csv\"");
		try {
			OutputStream outputStream = response.getOutputStream();
			String outputResult = "Transaction ID, Description, Date (YYYY-MM-DD), Amount\n";
			for (Transaction t : list) {
				outputResult += t.getTransactionId()+", ";
				outputResult += t.getTransactionType()+", ";
				outputResult += t.getDate()+", ";
				outputResult += t.getAmount();
				outputResult += "\n";
			}

			outputStream.write(outputResult.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */

	}
}
