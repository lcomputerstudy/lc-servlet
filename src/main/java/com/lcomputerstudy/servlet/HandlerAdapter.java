package com.lcomputerstudy.servlet;

public class HandlerAdapter {

	public static ModelAndView execute(ControllerAdapter controller, ModelAndView mv) {
		try {
			System.out.println("HandlerAdapter 가 Controller 를 실행");
			mv = (ModelAndView)controller.getMethod().invoke(controller.getInstance(), mv);
			System.out.println("ModelAndView 에 request, response, view 정보를 담아 반환");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

}
