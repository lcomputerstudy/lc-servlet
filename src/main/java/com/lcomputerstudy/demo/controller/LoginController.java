package com.lcomputerstudy.demo.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lcomputerstudy.demo.service.UserService;
import com.lcomputerstudy.demo.vo.User;
import com.lcomputerstudy.lcomputerframework.ApplicationContext;
import com.lcomputerstudy.lcomputerframework.ModelAndView;
import com.lcomputerstudy.lcomputerframework.annotation.Controller;
import com.lcomputerstudy.lcomputerframework.annotation.RequestMapping;

@Controller
public class LoginController{
	private UserService userService = (UserService)ApplicationContext.getBean("userService");
	
	@RequestMapping("/login.do")
	public ModelAndView loginForm(ModelAndView mv) {
		HttpServletRequest request = mv.getRequest();
		
		System.out.println("login.do request: "+request.getRequestURI());
		String param = request.getParameter("test");
		System.out.println("login.do getParameter: "+param);
		
		/*
		 * service 및 dao 작업 필요 (콜백 템플릿 패턴 사용)
		 * */
		User user = new User();
		user.setUserId("test1");
		user.setUserPassword("1234");
		user.setUserName("kim");
		user.setUserAge(20);
		user.setUserDatetime(new Timestamp(System.currentTimeMillis()));
		userService.addUser(user);
		System.out.println("insert user");
		
		List<User> list = userService.getUser(user.getUserId());
		
		System.out.println(list);
		System.out.println("select user list");
		
		request.setAttribute("user", user);
		request.setAttribute("list", list);
		
		System.out.println("login request: " + request + ", getParameter: " + request.getAttribute("user"));
		mv.setViewName("/user/loginForm"); 
		
		return mv;
	}
	
	@RequestMapping("/redirect.do")
	public ModelAndView redirect(ModelAndView mv) {
		HttpServletRequest request = mv.getRequest();
		String contextPath = request.getContextPath();
		
		mv.setRedirectUri(contextPath + "/main.do");
		return mv;
	}
	
	@RequestMapping("/main.do")
	public ModelAndView main(ModelAndView mv) {
		System.out.println("main.do 실행");
		
		mv.setViewName("/user/main");
		return mv;
	}

}