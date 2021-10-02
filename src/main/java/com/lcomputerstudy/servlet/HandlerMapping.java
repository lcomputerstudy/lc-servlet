package com.lcomputerstudy.servlet;

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
import javax.servlet.http.HttpServletResponse;

import com.lcomputerstudy.servlet.annotation.Controller;
import com.lcomputerstudy.servlet.annotation.RequestMapping;

public class HandlerMapping {
	
	private Map<String, Map<Object, String>> handlerMap = null;
		
	public HandlerMapping() {
		System.out.println("handlermapping 생성");
	}
	
	public void componentScan(ServletConfig config) {
		System.out.println("Component Scan");
		
		try {
			String packageName = config.getInitParameter("componentScanPackage");
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
		    for (Class<?> clazz : classes) {
		    	Annotation[] ctrlAnnos = clazz.getAnnotations();
		    	for (Annotation ctrlAnno : ctrlAnnos) {
					if (ctrlAnno instanceof Controller) {
						Method[] methods = clazz.getDeclaredMethods();
						for (Method method : methods) {
							Annotation[] annos = method.getAnnotations();
							for (Annotation anno : annos) {
								if (anno instanceof RequestMapping) {
									// reflection
									RequestMapping rmAnno = (RequestMapping)anno;
									String uri = rmAnno.value();
									String methodName = method.getName();
									Class<?> cls = Class.forName(clazz.getName());
									Object instance = cls.newInstance();
									//System.out.printf("url: %20s, class: %20s, method: %s%n", uri, clazz.getSimpleName(), methodName);
									
									Map<Object, String> ctrlMap = new HashMap<Object, String>();
									ctrlMap.put(instance, methodName);
									handlerMap.put(uri, ctrlMap);
								}
							}
						}
					}
		    	}
		    }
	    
		    // stream test
		    System.out.println("using Stream");
		    handlerMap.entrySet().stream().forEach( 
		    		m->m.getValue().entrySet().stream().forEach( 
		    				m2->System.out.printf("url: %-20s class: %-70s method: %-20s%n", m.getKey(), m2.getKey(), m2.getValue()) 
		    		)
		    );
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	public ModelAndView getController(HttpServletRequest request, HttpServletResponse response) {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String uri = requestURI.substring(contextPath.length());
		
		ModelAndView mv = null;
		
		try {
			Map<Object, String> ctrlMap = handlerMap.get(uri);
		    Set<Map.Entry<Object, String>> ctrlSet = ctrlMap.entrySet();
		    Iterator<Map.Entry<Object, String>> it = ctrlSet.iterator();
		    while (it.hasNext()) {
		    	Map.Entry<Object, String> entry = (Map.Entry<Object, String>)it.next();
		    	Object instance = entry.getKey();
		    	String methodName = entry.getValue();
		    	Method method = instance.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		    	System.out.println("methodName: "+methodName);
		    	mv = (ModelAndView)method.invoke(instance, request, response);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
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

