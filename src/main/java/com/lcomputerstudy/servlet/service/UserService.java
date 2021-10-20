package com.lcomputerstudy.servlet.service;

import com.lcomputerstudy.servlet.dao.UserDao;

public class UserService {
	private static UserDao userDao = null;
	
	// DAO를 DI 받기 위한 setter.
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
