package com.ilp.bankmgr.service;

import java.util.List;
import java.util.ArrayList;

import com.ilp.bankmgr.bean.Customer;
import com.ilp.bankmgr.dao.CustomerStatusDAO;

public class CustomerStatusService {
	public static ArrayList<Customer> searchCustomer()
	{
		CustomerStatusDAO customerFromDB = new CustomerStatusDAO();
		ArrayList<Customer> tr = new ArrayList<Customer>();
		tr = customerFromDB.viewCustomer();
		return tr;
	}

}
