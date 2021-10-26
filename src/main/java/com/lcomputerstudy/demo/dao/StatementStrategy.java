package com.lcomputerstudy.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	public PreparedStatement getPreparedStatement(Connection c) throws SQLException;
}
