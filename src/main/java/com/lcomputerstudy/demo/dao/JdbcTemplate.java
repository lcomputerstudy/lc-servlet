package com.lcomputerstudy.demo.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lcomputerstudy.database.DataSource;
import com.lcomputerstudy.servlet.exception.RuntimeSQLException;

public class JdbcTemplate {
	
	DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	// client
	public void update(String query, Object[] param) {
		templatePreparedStatement(new StatementStrategy() {
			public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(query);
				
				for (int i=0; i<param.length; i++) {
					setObject(pstmt, i+1, param[i]);
				}
				
				return pstmt;
			}
		});
	}

	// client	작업 필요
	public void query(String query, Object[] param) {
		templatePreparedStatement(new StatementStrategy() {
			public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(query);
				
				for (int i=0; i<param.length; i++) {
					setObject(pstmt, i+1, param[i]);
				}
					
				return pstmt;
			}
		});
	}
	
	// template
	public void templatePreparedStatement(StatementStrategy strategy) throws RuntimeSQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			pstmt = strategy.getPreparedStatement(conn);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeSQLException(e);
		} finally {
			if (rs != null ) {
				try { rs.close(); } catch (SQLException e) { throw new RuntimeSQLException(e); }
			}
			if (pstmt != null) { 
				try { pstmt.close(); } catch (SQLException e) { throw new RuntimeSQLException(e); } 
			}
			if (conn != null) { 
				try { conn.close(); } catch (SQLException e) { throw new RuntimeSQLException(e); } 
			}
		}
	}
	
	public void setObject(PreparedStatement pstmt, int index, Object obj)
		    throws SQLException {
	  	if (obj instanceof String) {
	  		pstmt.setString(index, (String)obj);
	  	} else if (obj instanceof Integer) {
	  		pstmt.setInt(index, (Integer)obj);
	  	} else if (obj instanceof Timestamp) {
	  		pstmt.setTimestamp(index, (Timestamp)obj);
	  	} else if (obj instanceof Long) {
	  		pstmt.setLong(index, (Long)obj);
	  	} else if (obj instanceof Array) {
	  		pstmt.setArray(index, (Array)obj);
	  	} else if (obj instanceof Float) {
	  		pstmt.setFloat(index, (Float)obj);
  		} else if (obj instanceof Double) {
	  		pstmt.setDouble(index, (Double)obj);
  		} else {
	  		throw new IllegalArgumentException();
		}
	}	

}
