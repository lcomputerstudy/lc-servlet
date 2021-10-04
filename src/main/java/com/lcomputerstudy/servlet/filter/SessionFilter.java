package com.lcomputerstudy.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter{
	FilterConfig filterConfig = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		this.filterConfig = filterConfig;
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		//HttpServletRequest req = (HttpServletRequest)request;
		//HttpServletResponse res = (HttpServletResponse)response;
		System.out.println("before filter");
		filterChain.doFilter(request, response);
		System.out.println("after filter");
		
		
	}
	
	@Override
	public void destroy() {
		Filter.super.destroy();
	}
	
}
