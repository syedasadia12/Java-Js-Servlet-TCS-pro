package com.ilp.bankmgr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ilp.bankmgr.bean.Customer;
import com.ilp.bankmgr.util.DBConnectionUtil;

public class CustomerStatusDAO {
	public ArrayList<Customer> viewCustomer()
	{
		ArrayList<Customer> cs = new ArrayList<Customer>();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnectionUtil.openConnection();
			ps = con.prepareStatement("SELECT * FROM ILP.customerstatus");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Customer cus = new Customer();
				cus.setCustomerId(rs.getInt("CUSTOMERID"));
				cus.setSsn(rs.getInt("SSNID"));
				cus.setName(rs.getString("CUSTOMERNAME"));
				cus.setAge(rs.getInt("CUSTOMERAGE"));
				cus.setAddress(rs.getString("CUSTOMERADDRESS"));
				cus.setState(rs.getString("CUSTOMERSTATE"));
				cus.setCity(rs.getString("CUSTOMERCITY"));
				cus.setMessage(rs.getString("MESSAGE"));
				cus.setDate(rs.getTimestamp("LASTUPDATE"));
				cs.add(cus);
			}
			
			return cs;
		}
		catch(SQLException ex) {
			System.out.println("Exception occured: "+ex.getMessage());
		}
		catch(Exception e){
			System.out.println("Exception occured: " +e.getMessage());
		}
		finally {
			try {
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return cs;
	}
}
