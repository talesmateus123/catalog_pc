/** 
 * This is the "ObjectNotFoundException" class. Which will be able to provide object not found exception handling.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
		
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
	
	
	
}
