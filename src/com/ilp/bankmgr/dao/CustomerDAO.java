package com.ilp.bankmgr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ilp.bankmgr.bean.Customer;
import com.ilp.bankmgr.util.DBConnectionUtil;



public class CustomerDAO {
	public static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USERNAME="ILP";
	public static final String PASSWORD="ILP";
	
	public Connection con =null;
	public ResultSet rs=null;
	
	public boolean createCustomer(Customer customer) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			con=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD); 
			
			
			String insertQuery="insert into CUSTOMERSTATUS values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insertQuery); 
			
			//change index to reflect column order in db
			ps.setInt(1, customer.getCustomerId());
			ps.setInt(2, customer.getSsn());
			ps.setString(3, customer.getName());
			ps.setInt(4, customer.getAge());
			ps.setString(5,customer.getAddress());
			ps.setString(6,customer.getCity());
			ps.setString(7,customer.getState());
			ps.setString(8,customer.getMessage());
			ps.setString(9,"Active");
			Timestamp t = new Timestamp(customer.getDate().getTime()); 
			ps.setTimestamp(10, t);
			
		
			
			int count =ps.executeUpdate();
			
			if(count >0) {
				return true;
			}
			
		}
		catch(ClassNotFoundException | SQLException ex) {
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
	
	public Customer searchCustomerById(int id){
		
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			con=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD); 
			
			String selecttQuery="select * from CustomerStatus where customerID=?";
			PreparedStatement ps = con.prepareStatement(selecttQuery); 
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
		
			
			while(rs.next()) {
				
				customer = new Customer(rs.getInt("SSNID"),rs.getInt("CUSTOMERID"),rs.getString("CUSTOMERNAME"),rs.getInt("CUSTOMERAGE"),rs.getString("CUSTOMERADDRESS"),rs.getString("CUSTOMERSTATE"),rs.getString("CUSTOMERCITY"),rs.getString("MESSAGE"),rs.getTimestamp("LASTUPDATE"));
				
			}
			
		}
		catch(ClassNotFoundException | SQLException ex) {
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
		
		
		return customer;
	}
	
	public Customer searchCustomerBySsnId(int Ssnid){
		
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			con=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD); 
			
			String selecttQuery="select * from CustomerStatus where SSNID=?";
			PreparedStatement ps = con.prepareStatement(selecttQuery); 
			ps.setInt(1, Ssnid);
			
			rs = ps.executeQuery();
			
		
			
			while(rs.next()) {
				
				customer = new Customer(rs.getInt("SSNID"),rs.getInt("CUSTOMERID"),rs.getString("CUSTOMERNAME"),rs.getInt("CUSTOMERAGE"),rs.getString("CUSTOMERADDRESS"),rs.getString("CUSTOMERSTATE"),rs.getString("CUSTOMERCITY"),rs.getString("MESSAGE"),rs.getTimestamp("LASTUPDATE"));
				
			}
			
		}
		catch(ClassNotFoundException | SQLException ex) {
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
		
		
		return customer;
	}
	
	
public boolean updateCustomer(Customer customer) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			con=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD); 
			
			
			String updateQuery="update CUSTOMERSTATUS set CUSTOMERNAME=?, CUSTOMERAGE=?, CUSTOMERADDRESS=?, CUSTOMERCITY=?, CUSTOMERSTATE=?, MESSAGE=?, LASTUPDATE=? where CUSTOMERID=?";
			PreparedStatement ps = con.prepareStatement(updateQuery); 
			
			
			ps.setString(1, customer.getName());
			ps.setInt(2, customer.getAge());
			ps.setString(3,customer.getAddress());
			ps.setString(4,customer.getCity());
			ps.setString(5,customer.getState());
			customer.setMessage("Updated");
			ps.setString(6,customer.getMessage());
			Date date = new Date();
			Timestamp t = new Timestamp(date.getTime()); 
			ps.setTimestamp(7, t);
			ps.setInt(8, customer.getCustomerId());
			
			
		
			
			int count =ps.executeUpdate();
			
			if(count >0) {
				return true;
			}
			
		}
		catch(ClassNotFoundException | SQLException ex) {
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

public boolean deleteCustomer(Customer cust) {
	// TODO Auto-generated method stub
	Connection con = null;
	try {
		con = DBConnectionUtil.openConnection();
		String sql = "Delete from customerStatus where customerid=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, cust.getCustomerId());
		
		int num = stmt.executeUpdate();
		if(num>0) return true;
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		DBConnectionUtil.closeConnection(con);
	}
	
	return false;
}
	
}
