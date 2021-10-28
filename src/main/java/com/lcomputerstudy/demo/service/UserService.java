package com.lcomputerstudy.demo.service;

import java.util.List;

import com.lcomputerstudy.demo.dao.UserDao;
import com.lcomputerstudy.demo.vo.User;

public class UserService {
	private UserDao userDao = null;
	
	// DAO를 DI 받기 위한 setter.
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void addUser(User user) {
		userDao.addUser(user);
	}
	
	public List<User> getUser(String userId) {
		return userDao.getUser(userId);
	}
}
