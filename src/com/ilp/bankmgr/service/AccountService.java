package com.ilp.bankmgr.service;

import java.util.ArrayList;
import java.util.List;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.dao.AccountDAO;

public class AccountService {

	public static boolean createAccount(Account acct) {
		AccountDAO dao = new AccountDAO();
		return dao.createAccount(acct);
	}

	public static Account searchAccountByAccountId(int id) {
		AccountDAO dao = new AccountDAO();
		return dao.searchAccountByAccountId(id);
	}

	public static List<Account> searchAcountByCustomerId(int cid) {
		AccountDAO dao = new AccountDAO();
		return dao.searchAccountByCustomerId(cid);
	}
	public static List<Account> searchAcountByCustomerSSN(int ssn) {
		AccountDAO dao = new AccountDAO();
		List<Account> accounts = dao.searchAccountBySSN(ssn);
		
		return accounts;
	}

	public boolean deleteAccountById(int accountId) {
		AccountDAO dao = new AccountDAO();
		Account acc = dao.searchAccountByAccountId(accountId);
		if(acc==null) return false;
		TransactionService.deleteRecordByAccountId(accountId);
		dao.deleteAccountById(accountId);
		
		acc = dao.searchAccountByAccountId(accountId);
		
		return acc==null?true:false;
	}
}
