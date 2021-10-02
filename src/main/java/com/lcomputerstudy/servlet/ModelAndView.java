package com.lcomputerstudy.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModelAndView {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	
	private String msg;
	public ModelAndView() {
		
	}
	
	public ModelAndView(String msg) {
		this.msg = msg;
	}
	
	public void setModel(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	@Override
	public String toString() {
		return msg;
	}
}
