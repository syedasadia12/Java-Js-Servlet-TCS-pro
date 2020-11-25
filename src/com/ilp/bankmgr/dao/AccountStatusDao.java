package com.ilp.bankmgr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.util.DBConnectionUtil;

public class AccountStatusDao {
	
	public ArrayList<Account> viewAllAccounts() {
		
		
		ArrayList<Account> ts = new ArrayList<Account>();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnectionUtil.openConnection();
			ps = con.prepareStatement("SELECT * FROM ILP.accountstatus order by lasttransaction desc");
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				Account acc = new Account();
				acc.setCustomerId(rs.getInt("CUSTOMERID"));
				acc.setAccountId(rs.getInt("ACCOUNTID"));
				acc.setAccountType(rs.getString("ACCOUNTTYPE"));
				acc.setMessage(rs.getString("MESSAGE"));
				acc.setStatus(rs.getString("STATUS"));
				acc.setDate(rs.getTimestamp("LASTTRANSACTION"));
				acc.setBalance(rs.getInt("ACCOUNTBALANCE"));
				ts.add(acc);

			}
			return ts;
			
		} catch(SQLException ex) {
			System.out.println("Exception occured: " + ex.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception occured: " + e.getMessage());
		}
		finally {
			try {
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return null;
			
	}
	
	
}
