package com.ilp.bankmgr.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
	public static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String CLASS_PATH = "oracle.jdbc.driver.OracleDriver";
	public static final String USER = "ILP";
	public static final String PASSWORD = "ILP";
	

	public static Connection openConnection() throws SQLException {
		try {
			Class.forName(CLASS_PATH);
			Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			return con;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

}
