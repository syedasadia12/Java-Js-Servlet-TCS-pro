package com.ilp.bankmgr.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ilp.bankmgr.bean.User;
import com.ilp.bankmgr.util.DBConnectionUtil;

public class UserDAO {

	public void retreiveUser(String username, String password) {
		
		
	}

	public User findUser(String username, String password) {
		boolean found = false;
		Connection con = null;
		try {
			con = DBConnectionUtil.openConnection();
			String sql = "select * from USERSTORE where username=? and password=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, username);
			pstm.setString(2, password);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()){
				String userName = rs.getString("username");
				String empName = rs.getString("employeeName");
				String userType = rs.getString("userType");
				Timestamp date = rs.getTimestamp("lastLogin");
				User user = new User(userName, empName, userType, date);
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con!=null) {
				DBConnectionUtil.closeConnection(con);
			}
		}
		return null;
	}

	
}
