package com.pml.services.exceptions;

public class ConflictOfObjectsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ConflictOfObjectsException(String msg) {
		super(msg);
		
	}
	
	public ConflictOfObjectsException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
}
