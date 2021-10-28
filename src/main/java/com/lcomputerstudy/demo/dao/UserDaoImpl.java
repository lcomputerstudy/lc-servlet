package com.lcomputerstudy.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lcomputerstudy.demo.vo.User;

public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate = null;
	
	private ResultMapper<User> userMapper = new ResultMapper<User>() {
		@Override
		public User resultMap(ResultSet rs) throws SQLException {
			User user = new User();
			user.setUserId(rs.getString("u_id"));
			user.setUserPassword(rs.getString("u_password"));
			user.setUserName(rs.getString("u_name"));
			user.setUserAge(rs.getInt("u_age"));
			return user;
		}
	};
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void addUser(User user) {
		jdbcTemplate.update("insert into lc_user (u_id, u_password, u_name, u_age) value (?, ?, ?, ?)", 
						new Object [] { 
								user.getUserId(), 
								user.getUserPassword(), 
								user.getUserName(), 
								user.getUserAge() 
						});
	}
	
	public List<User> getUser(String userId) {
		List<User> list = jdbcTemplate.query("select * from lc_user where u_id = ?", 
						new Object [] { userId }, 
						userMapper);
		return list;
	}
	
}