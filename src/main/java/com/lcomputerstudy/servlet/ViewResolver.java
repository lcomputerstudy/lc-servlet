package com.lcomputerstudy.servlet;

import javax.servlet.ServletConfig;

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

}
