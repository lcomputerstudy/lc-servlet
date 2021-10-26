package com.lcomputerstudy.servlet.exception;

public class RuntimeSQLException extends RuntimeException {
	static final long serialVersionUID = 1L;

	public RuntimeSQLException(Exception e) {
		super(e);
	}
}
