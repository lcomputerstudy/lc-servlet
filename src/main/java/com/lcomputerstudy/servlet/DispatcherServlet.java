package com.lcomputerstudy.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 2003781372535288855L;
	
	private HandlerMapping handlerMapping = null;
	private ViewResolver viewResolver = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		System.out.println("init()");
		
		/*ServletContext servletContext = config.getServletContext();
		String dbId = servletContext.getInitParameter("dbId");
		String dbPassword = servletContext.getInitParameter("dbPassword");
		System.out.println(dbId);
		System.out.println(dbPassword);*/
		
		handlerMapping = new HandlerMapping();
		try {
			handlerMapping.componentScan(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		ModelAndView mv = handlerMapping.getController(request, response);
		String view = viewResolver.getView(mv.getView());
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(mv.getModel(), response);
	}
	
	
	
	
}
