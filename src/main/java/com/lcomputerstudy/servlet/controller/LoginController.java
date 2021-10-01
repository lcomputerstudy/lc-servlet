package com.lcomputerstudy.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcomputerstudy.servlet.annotation.RequestMapping;
import com.lcomputerstudy.servlet.annotation.Controller;

@Controller
public class LoginController{

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
	
	@RequestMapping("/main.do")
	public String test() {
		return null;
	}
	
	@RequestMapping("/main2.do")
	public String test2() {
		return null;
	}

}