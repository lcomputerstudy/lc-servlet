package com.lcomputerstudy.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcomputerstudy.servlet.ModelAndView;
import com.lcomputerstudy.servlet.annotation.Controller;
import com.lcomputerstudy.servlet.annotation.RequestMapping;

@Controller
public class UserController {
	@RequestMapping("/board/list.do")
	public ModelAndView boardList(ModelAndView mv) {
		return mv;
	}
	
	@RequestMapping("/writeForm.do")
	public ModelAndView writeForm(ModelAndView mv) {
		return mv;
	}
}
