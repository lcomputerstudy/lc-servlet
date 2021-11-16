package com.lcomputerstudy.lcomputerframework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class HandlerItem {
	private Method method;
	private Annotation annotation;
	private Class<?> clazz;
	
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Annotation getAnnotation() {
		return annotation;
	}
	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}	
}

