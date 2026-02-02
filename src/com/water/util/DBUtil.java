package com.water.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getDBConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String URL="jdbc:oracle:thin:@localhost:1521:XE";
			String USER="water_sys";
			String PASS="root";
			Connection connection=DriverManager.getConnection(URL,USER,PASS);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
