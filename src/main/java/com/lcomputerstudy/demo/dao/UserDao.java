package com.lcomputerstudy.demo.dao;

import java.util.List;

import com.lcomputerstudy.demo.vo.User;

public interface UserDao {
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	public void addUser(User user);
	public List<User> getUser(String userId);
}
