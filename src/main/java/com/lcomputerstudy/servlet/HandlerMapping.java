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
import java.util.stream.Stream;

import com.lcomputerstudy.servlet.annotation.Controller;
import com.lcomputerstudy.servlet.annotation.RequestMapping;

public class HandlerMapping {
	
	private static HandlerMapping hm = null;
	
	private HandlerMapping() {
	}
	
	public static HandlerMapping getInstance() {
		if (hm == null) {
			hm = new HandlerMapping();
		}
		return hm;
	}
	
	public void componentScan() {
		
	}
	
	public void getController() {
		//System.out.println(Controller.class.);
		
		String packageName = "com.lcomputerstudy.servlet"; 
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    String path = packageName.replace('.', '/');
	    
	    List<Class<?>> classes = new ArrayList<Class<?>>();
	    try {
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
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    Map<String, Map<Object, String>> map = new HashMap<String, Map<Object, String>>();
	    
	    try {
		    for (Class<?> clazz : classes) {
		    	Annotation[] ctrlAnnos = clazz.getAnnotations();
		    	for (Annotation ctrlAnno : ctrlAnnos) {
					if (ctrlAnno instanceof Controller) {
						Method[] methods = clazz.getDeclaredMethods();
						for (Method method : methods) {
							Annotation[] annos = method.getAnnotations();
							for (Annotation anno : annos) {
								if (anno instanceof RequestMapping) {
									RequestMapping rmAnno = (RequestMapping)anno;
									String url = rmAnno.value();
									String methodName = method.getName();
									String className = clazz.getSimpleName();
									Class<?> cls = Class.forName(clazz.getName());
									Object instance = cls.newInstance();
									System.out.printf("url: %s, class: %s, method: %s%n", url, className, methodName);
									
									Map<Object, String> ctrlMap = new HashMap<Object, String>();
									ctrlMap.put(instance, methodName);
									map.put(url, ctrlMap);
								}
							}
						}
					}
		    	}
		    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    
	    try {
		    map.entrySet().stream().forEach( 
		    		m->m.getValue().entrySet().stream().forEach( 
		    				m2->System.out.printf("m:%s, k:%s, v:%s%n", m.getKey(), m2.getKey(), m2.getValue()) 
		    		)
		    );
		    
		    Map<Object, String> tmpCls = map.get("/board/list.do");
		    Set<Map.Entry<Object, String>> set = tmpCls.entrySet();
		    Iterator<Map.Entry<Object, String>> it = set.iterator();
		    while (it.hasNext()) {
		    	Map.Entry<Object, String> entry2 = (Map.Entry<Object, String>)it.next();
		    	Object instance = entry2.getKey();
		    	String methodName = entry2.getValue();
		    	Method method = instance.getClass().getMethod(methodName);
		    	System.out.println("run: " + method.invoke(instance));
		    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    
		
		
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

