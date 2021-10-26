package com.lcomputerstudy.demo.controller;

import com.lcomputerstudy.servlet.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import com.lcomputerstudy.demo.service.UserService;
import com.lcomputerstudy.demo.vo.User;
import com.lcomputerstudy.servlet.ApplicationContext;
import com.lcomputerstudy.servlet.ModelAndView;
import com.lcomputerstudy.servlet.annotation.Controller;

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
		userService.insertUser(user);
		System.out.println("insert user");
		
		request.setAttribute("user", user);
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