package com.lcomputerstudy.lcomputerframework;

import javax.servlet.ServletConfig;

public class ViewResolver {
	private static String prefix = null;
	private static String suffix = null;
	
	public static void init(ServletConfig config) {
		prefix = config.getInitParameter("prefix");
		suffix = config.getInitParameter("suffix");
	}

	public static ModelAndView setView(ModelAndView mv) {
		String fullPathView = prefix + mv.getViewName() + suffix;
		
		System.out.println("ViewResolver 가 View 를 생성 및 설정: " + fullPathView);
		
		View view = new View(fullPathView);
		mv.setView(view);
		
		return mv;
	}

}
