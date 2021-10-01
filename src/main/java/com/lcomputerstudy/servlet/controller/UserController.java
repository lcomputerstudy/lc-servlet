package com.lcomputerstudy.servlet.controller;

import com.lcomputerstudy.servlet.annotation.Controller;
import com.lcomputerstudy.servlet.annotation.RequestMapping;

@Controller
public class UserController {
	@RequestMapping("/board/list.do")
	public String boardList() {
		return "null 입니다~~~";
	}
	
	@RequestMapping("/writeForm.do")
	public String writeForm() {
		return "write not nul!!~~";
	}
}
