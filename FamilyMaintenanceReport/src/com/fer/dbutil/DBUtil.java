package com.fer.dbutil;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;

public class DBUtil {
	
	public static Connection getConnection() {

		/*try {
			System.out.println("registering driver");
			Class.forName("com.mysql.jdbc.Driver");

			return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/fer", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		DataSource dataSource = null;
		Connection connection = null;
		
        
		try {
			 Context initContext = new InitialContext();					 
			 Context envContext = (Context) initContext.lookup("java:comp/env");
	         dataSource = (DataSource) envContext.lookup("jdbc/fer");
	           
			 connection = (Connection) dataSource.getConnection();
					
		}
				 catch (SQLException e) {
					e.printStackTrace();
				}
		 catch (NamingException e) {
			e.printStackTrace();
		}
		return connection;
		
	//	return null;
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
