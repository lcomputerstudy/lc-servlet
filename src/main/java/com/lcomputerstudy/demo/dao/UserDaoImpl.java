package com.lcomputerstudy.demo.dao;

import com.lcomputerstudy.demo.vo.User;

public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate = null;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void insertUser(User user) {
		jdbcTemplate.update("insert into lc_user (u_id, u_password, u_name) value (?, ?, ?)", new Object [] { user.getUserId(), user.getUserPassword(), user.getUserName() } );
	}
	
	public User getUser(User user) {
		//jdbcTemplate.query("select * from lc_user where u_id = ?", resultMap, resultSet);
		/*User userResult = new User();
		
		jdbcTemplate.query("select * from user where userid = ?", new Object [] {}, );
		
		jdbcTemplate.sql( 
			(conn) -> {
				conn.prepareStatement("select * from user where userid = ?");
				conn.setString(user.getUserId());
			} 
		);*/
		
		return null;
	}
	
}