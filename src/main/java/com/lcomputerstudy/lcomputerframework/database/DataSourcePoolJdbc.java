package com.lcomputerstudy.lcomputerframework.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.mariadb.jdbc.MariaDbPoolDataSource;

public class DataSourcePoolJdbc implements DataSource {

	MariaDbPoolDataSource pool = null;
	
	public DataSourcePoolJdbc() {
		String server = "jdbc:mysql://localhost:3306/lcomputer";
		String id = "root";
		String password = "1234";
		pool = new MariaDbPoolDataSource(server+"?user="+id+"&password="+password+"&maxPoolSize=10");
	}
	
	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = pool.getConnection();
		
		return conn;
	}

}
