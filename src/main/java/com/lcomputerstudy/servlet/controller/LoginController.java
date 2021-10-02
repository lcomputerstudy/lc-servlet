package com.lcomputerstudy.servlet.controller;

import com.lcomputerstudy.servlet.annotation.RequestMapping;
import com.lcomputerstudy.servlet.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcomputerstudy.servlet.ModelAndView;
import com.lcomputerstudy.servlet.annotation.Controller;

@Controller
public class LoginController{
	
	@RequestMapping("/main.do")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("main.do request: "+request.getRequestURI());
		String param = request.getParameter("test");
		System.out.println("main.do getParameter: "+param);
		
		User user = new User();
		String view = "/main.jsp";
		
		return new ModelAndView("ok test"+param);
	}
	
	@RequestMapping("/main2.do")
	public ModelAndView test2(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("main2~~~~");
	}

}