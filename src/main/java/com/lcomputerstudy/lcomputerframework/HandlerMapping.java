package com.lcomputerstudy.lcomputerframework;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.lcomputerstudy.lcomputerframework.annotation.Controller;
import com.lcomputerstudy.lcomputerframework.annotation.RequestMapping;

public class HandlerMapping {
	
	private static Map<String, Map<Object, String>> handlerMap = null;
	
	public static void init(ServletConfig config) {
		try {
			String packageName = config.getInitParameter("controllerPackage");
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		    String path = packageName.replace('.', '/');
		    
		    List<Class<?>> classes = new ArrayList<Class<?>>();
		    Enumeration<URL> resources = classLoader.getResources(path);
		    List<File> dirs = new ArrayList<File>();
		    while (resources.hasMoreElements()) {
		        URL resource = resources.nextElement();
		        URI uri = new URI(resource.toString());
		        dirs.add(new File(uri.getPath()));
		    }
		    
		    for (File directory : dirs) {
		        classes.addAll(findClasses(directory, packageName));
		    }
		    
		    handlerMap = new HashMap<String, Map<Object, String>>();
		    
		    scanHandlerMapping(classes);
	    
		    printHandlerMapping();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printHandlerMapping() {
		// stream test
	    System.out.println("HandlerMapping 에 매핑된 Controller 목록:");
	    handlerMap.entrySet().stream().forEach( 
	    		m->m.getValue().entrySet().stream().forEach( 
	    				m2->System.out.printf("url: %-20s class: %-70s method: %-20s%n", m.getKey(), m2.getKey(), m2.getValue()) 
	    		)
	    );
	}

	private static void scanHandlerMapping(List<Class<?>> classes) {
		for (Class<?> clazz : classes) {
	    	getMethodsFromAnnotation(clazz);
		}
	}

	private static void getMethodsFromAnnotation(Class<?> clazz) {
		for (Annotation anno : clazz.getAnnotations()) {
			if (anno instanceof Controller) {
				getAnnotationsFromMethod(clazz);
			}
		}
	}

	private static void getAnnotationsFromMethod(Class<?> clazz) {
		for (Method method : clazz.getDeclaredMethods()) {
			
			HandlerItem item = new HandlerItem();
			item.setClazz(clazz);
			item.setMethod(method);
			
			getRequestMapping(item);
		}
	}

	private static void getRequestMapping(HandlerItem item) {
		for (Annotation anno : item.getMethod().getAnnotations()) {
			item.setAnnotation(anno);
			setHandlerMapping(item);
		}
	}

	private static void setHandlerMapping(HandlerItem item) {
		Annotation anno = item.getAnnotation();
		if (anno instanceof RequestMapping) {			
			try {
				handlerMap.put(((RequestMapping)anno).value(), setControllerMap(item));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static Map<Object, String> setControllerMap(HandlerItem item) throws Exception {
		Map<Object, String> map = new HashMap<Object, String>();
		
		map.put(item.getClazz().newInstance(), item.getMethod().getName());
		
		return map; 
	}

	public static ControllerAdapter getController(ModelAndView mv) {
		System.out.println("HandlerMapping 이 요청 URI에 해당하는 Controller 를 반환");
		HttpServletRequest request = mv.getRequest();
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String uri = requestURI.substring(contextPath.length());
		
		ControllerAdapter controller = null;
		
		try {
			Map<Object, String> ctrlMap = handlerMap.get(uri);
		    Set<Map.Entry<Object, String>> ctrlSet = ctrlMap.entrySet();
		    Iterator<Map.Entry<Object, String>> it = ctrlSet.iterator();
		    if (it.hasNext()) {
		    	Map.Entry<Object, String> entry = (Map.Entry<Object, String>)it.next();		    	
		    	
				Object instance = entry.getKey();
		    	String methodName = entry.getValue();
		    	Method method = instance.getClass().getMethod(methodName, ModelAndView.class);
		    	
		    	controller = new ControllerAdapter();
		    	controller.setInstance(instance);
		    	controller.setMethodName(methodName);
		    	controller.setMethod(method);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return controller;
	}
	
	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
	    List<Class<?>> classes = new ArrayList<Class<?>>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            classes.addAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
	        }
	    }
	    return classes;
	}
}

