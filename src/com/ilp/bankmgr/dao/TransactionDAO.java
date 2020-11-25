
package com.ilp.bankmgr.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ilp.bankmgr.bean.Transaction;
import com.ilp.bankmgr.util.DBConnectionUtil;

public class TransactionDAO {

	public static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USERNAME = "ILP";
	public static final String PASSWORD = "ILP";

	public Connection con = null;
	public ResultSet rs = null;

	public List<Transaction> getTransactions(int accountId, int num) {

		List<Transaction> trans=new ArrayList<Transaction>();; //List of transactions to be returned

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			String selectQuery = "select * from transaction where accountId=? and rownum<=?";
			PreparedStatement ps = con.prepareStatement(selectQuery);

			// change index to reflect column order in db
			ps.setInt(1, accountId);
			ps.setInt(2, num);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transaction t = new Transaction(rs.getInt("ACCOUNTID"), rs.getString("TRANSACTIONTYPE"),
						rs.getDouble("AMOUNT"), rs.getInt("CUSTOMERID"), rs.getTimestamp("TRANSACTIONDATE"));
				trans.add(t);
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

		return trans;
	}

	public List<Transaction> getTransactions(int accountId, Date startDate, Date endDate) {

		List<Transaction> trans=new ArrayList<Transaction>();; //List of transactions to be returned

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			String selectQuery = "select * from transaction where accountID=? and transactionDate between ? and ?";
			PreparedStatement ps = con.prepareStatement(selectQuery);

			// change index to reflect column order in db
			ps.setInt(1, accountId);
			ps.setDate(2, startDate);
			ps.setDate(3, endDate);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transaction t = new Transaction(rs.getInt("ACCOUNTID"), rs.getString("TRANSACTIONTYPE"),
						rs.getDouble("AMOUNT"), rs.getInt("CUSTOMERID"), rs.getTimestamp("TRANSACTIONDATE"));
				trans.add(t);
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

		return trans;
	}

	public boolean deposit(Transaction transaction) {
		try {
					
			con = DBConnectionUtil.openConnection();
			
			String selectQuery="SELECT COUNT(*) FROM TRANSACTION";
			PreparedStatement ps=con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int transactionId=100001001+rs.getInt("COUNT(*)");
			
			String insertQuery = "insert into TRANSACTION values(?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(insertQuery);
			
			ps.setInt(1, transactionId);
			ps.setInt(2, transaction.getAccountId());
			ps.setString(3, transaction.getTransactionType());
			ps.setDouble(4, transaction.getAmount());
			ps.setInt(5, transaction.getCustomerId());
			ps.setInt(6, transaction.getTargetAccountId());
			ps.setTimestamp(7, transaction.getDate());
			
			int count = ps.executeUpdate();
			
			String insertQuery1 = "update accountstatus set lasttransaction = ?, accountbalance = accountbalance + ? where accountid = ?";
			PreparedStatement ps1 = con.prepareStatement(insertQuery1);		
			
			ps1.setInt(3, transaction.getAccountId());
			ps1.setTimestamp(1, transaction.getDate());
			ps1.setDouble(2, transaction.getAmount());
			
			int count1 = ps1.executeUpdate();
				
			if(count > 0 && count1 > 0) {
				return true;
			}
			

		}
		catch(SQLException ex) {
			System.out.println("Exception occured: "+ex.getMessage());
		}
		catch(Exception e){
			System.out.println("Exception occured: " +e.getMessage());
		}
		finally {
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch(SQLException e) {
				System.out.println("Exception occured: " +e.getMessage());
			}
		}
		return false;	
	}
	
	public boolean withdraw(Transaction transaction) {
		try {
			
			con = DBConnectionUtil.openConnection();
			
			String selectQuery="SELECT COUNT(*) FROM TRANSACTION";
			PreparedStatement ps=con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int transactionId=100001001+rs.getInt("COUNT(*)");

			String insertQuery = "insert into TRANSACTION values(?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(insertQuery);
			
			ps.setInt(1, transactionId);
			ps.setInt(2, transaction.getAccountId());
			ps.setString(3, transaction.getTransactionType());
			ps.setDouble(4, transaction.getAmount());
			ps.setInt(5, transaction.getCustomerId());
			ps.setInt(6, transaction.getTargetAccountId());
			ps.setTimestamp(7, transaction.getDate());
			
			int count = ps.executeUpdate();
			
			String insertQuery1 = "update accountstatus set lasttransaction = ?, accountbalance = accountbalance - ? where accountid = ?";
			PreparedStatement ps1 = con.prepareStatement(insertQuery1);		
			
			ps1.setInt(3, transaction.getAccountId());
			ps1.setTimestamp(1, transaction.getDate());
			ps1.setDouble(2, transaction.getAmount());
			
			int count1 = ps1.executeUpdate();
				
			if(count > 0 && count1 > 0) {
				return true;
			}
			
		}
		catch(SQLException ex) {
			System.out.println("Exception occured: "+ex.getMessage());
		}
		catch(Exception e){
			System.out.println("Exception occured: " +e.getMessage());
		}
		finally {
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch(SQLException e) {
				System.out.println("Exception occured: " +e.getMessage());
			}
		}
		return false;
	}

	public boolean transfer(Transaction transaction) {
		try {
			
			con = DBConnectionUtil.openConnection();
			
			String selectQuery="SELECT COUNT(*) FROM TRANSACTION";
			PreparedStatement ps=con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int transactionId=100001001+rs.getInt("COUNT(*)");
			
			String insertQuery = "insert into TRANSACTION values(?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(insertQuery);
			
			ps.setInt(1, transactionId);
			ps.setInt(2, transaction.getAccountId());
			ps.setString(3, transaction.getTransactionType());
			ps.setDouble(4, transaction.getAmount());
			ps.setInt(5, transaction.getCustomerId());
			ps.setInt(6, transaction.getTargetAccountId());
			ps.setTimestamp(7, transaction.getDate());
			
			int count = ps.executeUpdate();
			
			String insertQuery1 = "update accountstatus set lasttransaction = ?, accountbalance = accountbalance - ? where accountid = ?";
			PreparedStatement ps1 = con.prepareStatement(insertQuery1);		
			
			ps1.setInt(3, transaction.getAccountId());
			ps1.setTimestamp(1, transaction.getDate());
			ps1.setDouble(2, transaction.getAmount());
			
			int count1 = ps1.executeUpdate();
			
			String insertQuery2 = "update accountstatus set lasttransaction = ?, accountbalance = accountbalance + ? where accountid = ?";
			PreparedStatement ps2 = con.prepareStatement(insertQuery2);		
			
			ps2.setInt(3, transaction.getTargetAccountId());
			ps2.setTimestamp(1, transaction.getDate());
			ps2.setDouble(2, transaction.getAmount());
			
			int count2 = ps2.executeUpdate();
				
			if(count > 0 && count1 > 0 && count2 > 0) {
				return true;
			}
			
		}
		catch(SQLException ex) {
			System.out.println("Exception occured: "+ex.getMessage());
		}
		catch(Exception e){
			System.out.println("Exception occured: " +e.getMessage());
		}
		finally {
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch(SQLException e) {
				System.out.println("Exception occured: " +e.getMessage());
			}
		}
		return false;
	}


	
	

	public void deleteRecorderByAccountId(int accountId) {
		Connection con = null;
		try {
			con = DBConnectionUtil.openConnection();
			String sql = "Delete From Transaction where accountID=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, accountId);
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.closeConnection(con);
		}

	}

}
