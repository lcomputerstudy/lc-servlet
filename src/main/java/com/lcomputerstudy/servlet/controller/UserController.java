package com.lcomputerstudy.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcomputerstudy.servlet.ModelAndView;
import com.lcomputerstudy.servlet.annotation.Controller;
import com.lcomputerstudy.servlet.annotation.RequestMapping;

@Controller
public class UserController {
	@RequestMapping("/board/list.do")
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView();
	}
	
	@RequestMapping("/writeForm.do")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}
