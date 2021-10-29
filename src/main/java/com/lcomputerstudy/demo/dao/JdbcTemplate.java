package com.lcomputerstudy.demo.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lcomputerstudy.database.DataSource;
import com.lcomputerstudy.servlet.exception.RuntimeSQLException;

public class JdbcTemplate {
	
	DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	// client
	public <T> void update(String query, Object[] param) {
		templatePreparedStatement(new StatementStrategyAdapter<T>() {
			@Override
			public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(query);
				setObjectForArray(pstmt, param);
				pstmt.executeUpdate();
				
				return pstmt;
			}
		});
	}

	// client
	public <T> List<T> query(String query, Object[] param, ResultMapper<T> mapper) {
		List<T> list = templatePreparedStatement(new StatementStrategyAdapter<T>() {
			@Override
			public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(query);				
				setObjectForArray(pstmt, param);
					
				return pstmt;
			}
			
			@Override
			public List<T> getResult(PreparedStatement pstmt) throws SQLException {
				ResultSet rs = pstmt.executeQuery();
				List<T> list = getList(rs, mapper);
				
				return list;
			}
			
		});
		
		return list;
	}
	
	// template
	public <T> List<T> templatePreparedStatement(StatementStrategy<T> strategy) throws RuntimeSQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			pstmt = strategy.getPreparedStatement(conn);
			List<T> list = strategy.getResult(pstmt);
			if (list != null)
				return list;
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeSQLException(e);
		} finally {
			if (pstmt != null) { 
				try { pstmt.close(); } catch (SQLException e) { throw new RuntimeSQLException(e); } 
			}
			if (conn != null) { 
				try { conn.close(); } catch (SQLException e) { throw new RuntimeSQLException(e); } 
			}
		}
	}
	
	// util
	public void setObjectForArray(PreparedStatement pstmt, Object[] param) throws SQLException {
		for (int i=0, index=1; i<param.length; i++, index++) {
		  	if (param[i] instanceof String) {
		  		pstmt.setString(index, (String)param[i]);
		  	} else if (param[i] instanceof Integer) {
		  		pstmt.setInt(index, (Integer)param[i]);
		  	} else if (param[i] instanceof Timestamp) {
		  		pstmt.setTimestamp(index, (Timestamp)param[i]);
		  	} else if (param[i] instanceof Long) {
		  		pstmt.setLong(index, (Long)param[i]);
		  	} else if (param[i] instanceof Array) {
		  		pstmt.setArray(index, (Array)param[i]);
		  	} else if (param[i] instanceof Float) {
		  		pstmt.setFloat(index, (Float)param[i]);
	  		} else if (param[i] instanceof Double) {
		  		pstmt.setDouble(index, (Double)param[i]);
	  		} else {
		  		throw new IllegalArgumentException();
			}
		}
	}
	
	// util
	public <T> List<T> getList(ResultSet rs, ResultMapper<T> mapper) throws SQLException {
		List<T> list = new ArrayList<T>();
		while (rs.next()) {
			T vo = null;
			vo = mapper.resultMap(rs);
			
			list.add(vo);
		}
		if (rs != null) rs.close();
		
		return list;
	}

}
