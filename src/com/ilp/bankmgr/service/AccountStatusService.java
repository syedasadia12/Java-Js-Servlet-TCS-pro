package com.ilp.bankmgr.service;


import java.util.ArrayList;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.dao.AccountStatusDao;

public class AccountStatusService {
	public static ArrayList<Account> searchAllAccounts(){
		AccountStatusDao allAccountsFromDB = new AccountStatusDao();
		ArrayList<Account> tree = new ArrayList<Account>();
		
		tree=allAccountsFromDB.viewAllAccounts();
		return tree;
		
		
	}
}