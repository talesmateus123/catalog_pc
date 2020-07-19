/** 
 * This is the "ConflictOfObjectsException" class. Which will be able to provide object conflict exception handling.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services.exceptions;

public class InvalidQueryException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidQueryException(String msg) {
		super(msg);
		
	}
	
	public InvalidQueryException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
	
	
	
}
