package com.lcomputerstudy.demo.dao;

import com.lcomputerstudy.demo.vo.User;

public interface UserDao {
	public void insertUser(User user);

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}
