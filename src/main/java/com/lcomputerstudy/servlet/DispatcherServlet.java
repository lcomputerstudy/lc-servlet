package com.lcomputerstudy.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Front Controller
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 2003781372535288855L;
	
	private HandlerMapping handlerMapping = null;
	private ViewResolver viewResolver = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		System.out.println("init()");
		
		// bean 생성 및 종속성 설정
		ApplicationContext.init();
		
		/*ServletContext servletContext = config.getServletContext();
		String dbId = servletContext.getInitParameter("dbId");
		String dbPassword = servletContext.getInitParameter("dbPassword");
		System.out.println(dbId);
		System.out.println(dbPassword);*/
		
		handlerMapping = new HandlerMapping(config);
		viewResolver = new ViewResolver(config);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
		System.out.println("service()");
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("destroy()");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
		
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("process");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mv = new ModelAndView(request, response);
		Controller controller = handlerMapping.getController(mv);
		HandlerAdapter.run(controller, mv);
		
		// view redirect 도 가능하게 modelandview controller 에서 생성하여 전달 받도록 수정 필요
		String view = viewResolver.getFullPathView(mv.getView());
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	
	
	
}
