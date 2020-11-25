package com.ilp.bankmgr.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Account {
	private int customerId;
	private int accountId;
	//change char to String
	private String accountType;
	private int balance;
	private String message;
	private Timestamp date;
	//Added status
	private String status;

	//Added status field in constructor
	public Account(int customerId, int accountId, String accountType, int balance, String message, Timestamp date, String status) {
		super();
		this.customerId = customerId;
		this.accountId = accountId;
		this.accountType = accountType;
		this.balance = balance;
		this.message = message;
		this.date = date;
		this.status = status;
	}
	
	//Added empty constructor for future addition
	public Account() {
		
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp timestamp) {
		this.date = timestamp;
	}

	//Added below getStatus and setStatus
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
