package com.lcomputerstudy.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcomputerstudy.lcomputerframework.ModelAndView;
import com.lcomputerstudy.lcomputerframework.annotation.Controller;
import com.lcomputerstudy.lcomputerframework.annotation.RequestMapping;

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
