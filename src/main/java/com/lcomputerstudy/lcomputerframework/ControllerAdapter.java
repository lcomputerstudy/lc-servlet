package com.lcomputerstudy.lcomputerframework;

import java.lang.reflect.Method;

public class ControllerAdapter {
	private Object instance = null;
	private String methodName = null;
	private Method method = null;
	
	public Object getInstance() {
		return instance;
	}
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	@Override
	public String toString() {
		return "Controller [instance=" + instance + ", methodName=" + methodName + ", method=" + method + "]";
	}
}
