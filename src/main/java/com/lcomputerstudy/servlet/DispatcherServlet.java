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
		System.out.println("DispatcherServlet (FrontController)");
		
		// bean 생성 및 종속성 설정
		ApplicationContext.init();
		
		/*ServletContext servletContext = config.getServletContext();
		String dbId = servletContext.getInitParameter("dbId");
		String dbPassword = servletContext.getInitParameter("dbPassword");
		System.out.println(dbId);
		System.out.println(dbPassword);*/
		
		HandlerMapping.init(config);
		ViewResolver.init(config);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service()");
		super.service(arg0, arg1);
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
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mv = new ModelAndView(request, response);
		ControllerAdapter controller = HandlerMapping.getController(mv);
		mv = HandlerAdapter.execute(controller, mv);
		ViewResolver.setView(mv);
		forwardOrRedirect(mv);
	}
	
	public static void forwardOrRedirect(ModelAndView mv) {
		HttpServletRequest request = mv.getRequest();
		HttpServletResponse response = mv.getResponse();
		String redirectUri = mv.getRedirectUri();
		
		try {
			if (redirectUri == null) {
				String view = mv.getView().getFullPathView();
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			} else {
				response.sendRedirect(redirectUri);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("destroy()");
	}
	
	
}
