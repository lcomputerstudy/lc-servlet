package com.lcomputerstudy.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	private String prefix = null;
	private String suffix = null;
	
	public ViewResolver(ServletConfig config) {
		this.prefix = config.getInitParameter("prefix");
		this.suffix = config.getInitParameter("suffix");
	}

	public String getFullPathView(String view) {
		return this.prefix + view + this.suffix;
	}

	public void view(ModelAndView mv) {
		HttpServletRequest request = mv.getRequest();
		HttpServletResponse response = mv.getResponse();
		String redirectUri = mv.getRedirectUri();
		
		try {
			if (redirectUri == null) {
				String view = getFullPathView(mv.getView());
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			} else {
				response.sendRedirect(redirectUri);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
