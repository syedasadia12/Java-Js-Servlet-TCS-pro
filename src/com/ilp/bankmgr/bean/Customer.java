package com.ilp.bankmgr.bean;

import java.util.Date;

public class Customer {
	private int ssn;
	private int customerId;
	private String name;
	private int age;
	private String address;
	private String state;
	private String city;
	private String message;
	private Date date;

	public Customer(int ssn, int customerId, String name, int age, String address, String state, String city,
			String message, Date date) {
		super();
		this.ssn = ssn;
		this.customerId = customerId;
		this.name = name;
		this.age = age;
		this.address = address;
		this.state = state;
		this.city = city;
		this.message = message;
		this.date = date;
	}
	public Customer() {
		
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
