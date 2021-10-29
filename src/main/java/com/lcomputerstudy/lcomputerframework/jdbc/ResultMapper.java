package com.lcomputerstudy.lcomputerframework.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultMapper<T> {
	public T resultMap(ResultSet rs) throws SQLException;
}
