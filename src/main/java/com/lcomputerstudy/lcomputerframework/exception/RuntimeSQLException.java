package com.lcomputerstudy.lcomputerframework.exception;

public class RuntimeSQLException extends RuntimeException {
	static final long serialVersionUID = 1L;

	public RuntimeSQLException(Exception e) {
		super(e);
	}
}
