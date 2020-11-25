package com.ilp.bankmgr.service;

import java.util.List;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.bean.Customer;
import com.ilp.bankmgr.dao.CustomerDAO;

public class CustomerService {
	
	public static boolean createCustomer(Customer customer) {
		CustomerDAO dao = new CustomerDAO();
		return dao.createCustomer(customer);
	}
	
	public static Customer searchCustomerById(int id) {
		CustomerDAO dao = new CustomerDAO();
		return dao.searchCustomerById(id);
	}
	
	public static  Customer searchCustomerBySsnId(int Ssnid) {
		CustomerDAO dao = new CustomerDAO();
		return dao.searchCustomerBySsnId(Ssnid);
	}
	
	public static boolean updateCustomer(Customer customer) {
		CustomerDAO dao = new CustomerDAO();
		return dao.updateCustomer(customer);
	}

	public static boolean deleteCustomer(Customer cust) {
		// TODO Auto-generated method stub
		CustomerDAO dao = new CustomerDAO();
		
		
		return dao.deleteCustomer(cust);
	}

}
