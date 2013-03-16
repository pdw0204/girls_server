package com.nd.girls.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nd.girls.model.NDGirl;



public class NDGirlDBDriver {
	
	private static Object synObj = new Object();
	
	private static NDGirlDBDriver db = null ;
	
	private NDGirlDBDriver() {
		
	}
	
	public static NDGirlDBDriver getDBDriver() {
		synchronized (synObj) {
			if (db == null)
				db = new NDGirlDBDriver() ;
			return db ;
		}
	}
	
	public boolean insert(NDGirl girl) {
		Connection con = getConnection() ;
		if (con != null) {
			String sql = "" ;
			try {
				java.sql.Statement sm = con.createStatement();
				return sm.executeUpdate(sql) > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null)
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		return false ;
	}
	
	public ArrayList<NDGirl> getGirls() {
		List<NDGirl> girls = new ArrayList<NDGirl>();
		Connection con = getConnection() ;
		if (con != null) {
			String sql = "select * from " ;
			try {
				java.sql.Statement sm = con.createStatement();
				ResultSet result = sm.executeQuery(sql);
				while(result.next()){
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null)
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		return null ;
	}
	
	
	private Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return  DriverManager.getConnection("jdbc:mysql://localhost:3306/myuser","root" ,"Pdw7183209" );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null ;
	}
	
}
