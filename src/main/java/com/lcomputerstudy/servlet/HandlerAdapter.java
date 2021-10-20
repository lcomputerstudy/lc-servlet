package com.lcomputerstudy.servlet;

import java.lang.reflect.Method;
import java.util.Map.Entry;

public class HandlerAdapter {

	public static void run(Controller controller, ModelAndView mv) {
		try {
			controller.getMethod().invoke(controller.getInstance(), mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
