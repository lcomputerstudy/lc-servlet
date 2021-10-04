package com.lcomputerstudy.servlet.controller;

import com.lcomputerstudy.servlet.annotation.RequestMapping;
import com.lcomputerstudy.servlet.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcomputerstudy.servlet.ModelAndView;
import com.lcomputerstudy.servlet.annotation.Controller;

@Controller
public class LoginController{
	
	@RequestMapping("/login.do")
	public ModelAndView loginForm(ModelAndView mv) {
		HttpServletRequest request = mv.getRequest();
		
		System.out.println("login.do request: "+request.getRequestURI());
		String param = request.getParameter("test");
		System.out.println("login.do getParameter: "+param);
		
		User user = new User();
		request.setAttribute("user", user);
		System.out.println("login request: " + request + ", getParameter: " + request.getAttribute("user"));
		mv.setView("/user/loginForm");
		
		return mv;
	}
	
	@RequestMapping("/main2.do")
	public ModelAndView test2(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView();
	}

}