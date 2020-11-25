package com.ilp.bankmgr.service;

import com.ilp.bankmgr.bean.User;
import com.ilp.bankmgr.dao.UserDAO;

public class UserService {

	public User login(String username, String password) {
		// TODO Auto-generated method stub
		UserDAO dao = new UserDAO();
		User user = dao.findUser(username,password);
		return user;
	}

}
