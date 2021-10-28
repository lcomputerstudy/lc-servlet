package com.lcomputerstudy.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface StatementStrategy<T> {
	public PreparedStatement getPreparedStatement(Connection c) throws SQLException;
	public List<T> getResult(PreparedStatement pstmt) throws SQLException;
}
