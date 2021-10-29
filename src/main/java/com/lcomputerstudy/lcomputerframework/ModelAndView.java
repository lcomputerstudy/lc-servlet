package com.lcomputerstudy.lcomputerframework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModelAndView {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private String viewName = null;
	private String redirectUri = null;
	private View view = null;
	
	public ModelAndView() {
		System.out.println("ModelAndView");
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

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public View getView() {
		return view;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

}
