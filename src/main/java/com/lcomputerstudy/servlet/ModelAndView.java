package com.lcomputerstudy.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModelAndView {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private String view = null;
	private String redirectUri = null;
	
	public ModelAndView() {
		
	}
	
	public ModelAndView(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setView(String view) {
		this.view = view;
	}
	
	public String getView() {
		return view;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

}
