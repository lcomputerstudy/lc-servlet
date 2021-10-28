package com.lcomputerstudy.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultMapper<T> {
	public T resultMap(ResultSet rs) throws SQLException;
}
