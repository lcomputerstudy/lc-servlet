package com.lcomputerstudy.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModelAndView {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private String view = null;
	
	public ModelAndView() {
		
	}
	
	public ModelAndView(HttpServletRequest request) {
		this(request, null);
	}
	
	public ModelAndView(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public void setModel(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletRequest getModel() {
		return this.request;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpServletResponse getResponse() {
		return this.response;
	}

	public void setView(String view) {
		this.view = view;
	}
	
	public String getView() {
		return view;
	}
}
