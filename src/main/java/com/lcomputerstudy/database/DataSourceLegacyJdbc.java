package com.lcomputerstudy.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceLegacyJdbc implements DataSource {

	public DataSourceLegacyJdbc() {

	}
	
	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		
		String url = "jdbc:mysql://localhost:3306/lcomputer";
		String id = "root";
		String pw = "1234";
				
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection(url, id, pw);
		
		return conn;
	}

}
