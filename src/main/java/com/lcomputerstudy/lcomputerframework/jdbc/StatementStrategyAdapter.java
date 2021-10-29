package com.lcomputerstudy.lcomputerframework.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class StatementStrategyAdapter<T> implements StatementStrategy<T> {

	@Override
	public PreparedStatement getPreparedStatement(Connection c) throws SQLException {
		return null;
	}

	@Override
	public List<T> getResult(PreparedStatement pstmt) throws SQLException {
		return null;
	}

}
