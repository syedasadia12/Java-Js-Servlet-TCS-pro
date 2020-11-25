package com.ilp.bankmgr.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
	private String username;
	private String empName;
	private String userType;
	private Timestamp lastLogin;

	public User(String username, String empName, String userType, Timestamp lastLogin) {
		super();
		this.username = username;
		this.empName = empName;
		this.userType = userType;
		this.lastLogin = lastLogin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

}
