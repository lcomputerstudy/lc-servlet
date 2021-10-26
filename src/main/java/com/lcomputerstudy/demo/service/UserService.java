package com.lcomputerstudy.demo.service;

import com.lcomputerstudy.demo.dao.UserDao;
import com.lcomputerstudy.demo.vo.User;

public class UserService {
	private UserDao userDao = null;
	
	// DAO를 DI 받기 위한 setter.
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void insertUser(User user) {
		userDao.insertUser(user);
	}
}
