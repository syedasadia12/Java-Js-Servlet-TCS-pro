package com.ilp.bankmgr.bean;

import java.sql.Timestamp;
import java.util.Date;

public class Transaction {

	private int transactionId;
	private int accountId;
	private String transactionType;
	private double amount;
	private int customerId;
	private int targetAccountId;
	private Timestamp date;
	
	public Transaction(int transactionId, int accountId, String transactionType, int amount, int customerId,
			int targetAccountId, Timestamp date) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.customerId = customerId;
		this.targetAccountId = targetAccountId;
		this.date = date;
	}

	
	public Transaction(int accountId, String transactionType, double amount, int customerId, Timestamp date) {
		super();
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.customerId = customerId;
		this.date = date;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactonType) {
		this.transactionType = transactonType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getTargetAccountId() {
		return targetAccountId;
	}

	public void setTargetAccountId(int targetAccountId) {
		this.targetAccountId = targetAccountId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}