package com.ilp.bankmgr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.ilp.bankmgr.bean.Account;
import com.ilp.bankmgr.util.DBConnectionUtil;

public class AccountDAO {
	// Base code belongs to Deveshwar, edited by Omar
	public static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USERNAME = "ILP";
	public static final String PASSWORD = "ILP";

	public Connection con = null;
	public ResultSet rs = null;

	public boolean createAccount(Account account) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			//Check for accounts number
			String selectQuery="SELECT COUNT(*) FROM ACCOUNTSTATUS";
			PreparedStatement ps=con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int accountID=111011000+rs.getInt("COUNT(*)");
			
			String insertQuery = "insert into ACCOUNTSTATUS values(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(insertQuery);

			// change index to reflect column order in db
			ps.setInt(1, account.getCustomerId());
			ps.setInt(2, accountID);
			ps.setString(3, String.valueOf(account.getAccountType()));
			ps.setDouble(7, account.getBalance());
			ps.setString(4, account.getMessage());
			ps.setTimestamp(6, account.getDate()); //Last Transaction
			ps.setString(5, account.getStatus());

			int count = ps.executeUpdate();

			if (count > 0) {
				return true;
			}

		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("Exception occured: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("Exception occured: " + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("Exception occured: " + e.getMessage());
			}
		}

		return false;
	}

	public List<Account> searchAccountByCustomerId(int cid) {

		List<Account> accounts = new ArrayList<Account>();
		Account acc = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			String selecttQuery = "select * from AccountStatus where customerID=?";
			PreparedStatement ps = con.prepareStatement(selecttQuery);
			ps.setInt(1, cid);

			rs = ps.executeQuery();

			while (rs.next()) {

				acc = new Account(rs.getInt("CUSTOMERID"), rs.getInt("ACCOUNTID"), rs.getString("ACCOUNTTYPE"),
						rs.getInt("ACCOUNTBALANCE"), rs.getString("MESSAGE"), rs.getTimestamp("LASTTRANSACTION"),rs.getString("STATUS"));
				accounts.add(acc);
			}

		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("Exception occured: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("Exception occured: " + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("Exception occured: " + e.getMessage());
			}
		}

		return accounts;
	}

	public Account searchAccountByAccountId(int accid) {

		//List<Account> accounts = new ArrayList<Account>();
		Account acc = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			String selecttQuery = "select * from AccountStatus where AccountID=?";
			PreparedStatement ps = con.prepareStatement(selecttQuery);
			ps.setInt(1, accid);

			rs = ps.executeQuery();

			while (rs.next()) {

				acc = new Account(rs.getInt("CUSTOMERID"), rs.getInt("ACCOUNTID"), rs.getString("ACCOUNTTYPE"),
						rs.getInt("ACCOUNTBALANCE"), rs.getString("MESSAGE"), rs.getTimestamp("LASTTRANSACTION"),rs.getString("STATUS"));
				//accounts.add(acc);
			}

		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("Exception occured: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("Exception occured: " + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("Exception occured: " + e.getMessage());
			}
		}

		return acc;
	}




	public List<Account> searchAccountBySSN(int ssn) {
		// TODO Auto-generated method stub
		List<Account> accounts = new ArrayList<Account>();
		Account acc = null;

		try {
			

			con = DBConnectionUtil.openConnection();

			String selecttQuery = "select * from AccountStatus join CustomerStatus on AccountStatus.customerID=CustomerStatus.customerID where CustomerStatus.ssnID=?";
			PreparedStatement ps = con.prepareStatement(selecttQuery);
			ps.setInt(1, ssn);

			rs = ps.executeQuery();

			while (rs.next()) {

				acc = new Account(rs.getInt("CUSTOMERID"), rs.getInt("ACCOUNTID"), rs.getString("ACCOUNTTYPE"),
						rs.getInt("ACCOUNTBALANCE"), rs.getString("MESSAGE"), rs.getTimestamp("LASTTRANSACTION"),rs.getString("STATUS"));
				accounts.add(acc);
			}

		} catch (SQLException ex) {
			System.out.println("Exception occured: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("Exception occured: " + e.getMessage());
		} finally {
			DBConnectionUtil.closeConnection(con);
		}

		return accounts;
		
	}

	public boolean deleteAccountById(int accountId) {
		boolean success = false;
		try {
			con = DBConnectionUtil.openConnection();
			String sql = "Delete from AccountStatus where accountID=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, accountId);
			if(stmt.executeUpdate()>0) {
				success = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			DBConnectionUtil.closeConnection(con);
		}
		return success;
		
	}

}
