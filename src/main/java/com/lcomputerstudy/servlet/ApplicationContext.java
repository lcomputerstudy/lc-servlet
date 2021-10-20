package com.lcomputerstudy.servlet;

import java.util.HashMap;
import java.util.Map;

import com.lcomputerstudy.servlet.dao.UserDao;
import com.lcomputerstudy.servlet.dao.UserDaoImpl;
import com.lcomputerstudy.servlet.service.UserService;

// bean 생성 및 관리 (컨테이너라고 부름)
public class ApplicationContext {
	public static Map<String, Object> beans = new HashMap<String, Object>();
	public static UserService userService = null;
	public static UserDao userDao = null;
	
	public static void init() {
		// bean 생성
		userService = new UserService();
		userDao = new UserDaoImpl();
		
		// bean 종속성 설정
		userService.setUserDao(userDao);
		
		// bean 등록
		beans.put("userService", userService);
	}
	
	public static Object getBean(String beanName) {
		return beans.get(beanName);
	}
}
