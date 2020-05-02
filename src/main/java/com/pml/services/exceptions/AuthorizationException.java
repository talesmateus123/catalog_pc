/**
 * This is the "DataIntegrityException" class. Which will be able to provide data integrity exception handling.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services.exceptions;

public class AuthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String msg) {
		super(msg);
		
	}
	
	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
	
	
	
}
