package com.lcomputerstudy.lcomputerframework.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {

	public Connection getConnection() throws SQLException, ClassNotFoundException;

}
