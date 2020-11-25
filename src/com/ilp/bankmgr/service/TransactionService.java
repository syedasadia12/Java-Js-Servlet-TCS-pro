package com.ilp.bankmgr.service;

import java.sql.Date;
import java.util.List;

import com.ilp.bankmgr.bean.Transaction;
import com.ilp.bankmgr.dao.TransactionDAO;

public class TransactionService {

	public static void deleteRecordByAccountId(int accountId) {
		TransactionDAO dao = new TransactionDAO();
		dao.deleteRecorderByAccountId(accountId);

	}
	public static List<Transaction> getTransactions(int accountId, int num) {
		TransactionDAO dao = new TransactionDAO();
		return dao.getTransactions(accountId,num);
	}

	public static List<Transaction> getTransactions(int accountId, Date startDate, Date endDate) {
		TransactionDAO dao = new TransactionDAO();
		return dao.getTransactions(accountId,startDate,endDate);
	}


	public static boolean withdraw(Transaction transaction) {
		TransactionDAO dao = new TransactionDAO();
		return dao.withdraw(transaction);
	}

	public static boolean deposit(Transaction transaction) {
		TransactionDAO dao = new TransactionDAO();
		return dao.deposit(transaction);
	}

	public static boolean transfer(Transaction transaction) {
		TransactionDAO dao = new TransactionDAO();
		return dao.transfer(transaction);
	}



}
