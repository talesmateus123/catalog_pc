/**
 * This is the "DataIntegrityException" class. Which will be able to provide data integrity exception handling.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services.exceptions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
		super(msg);
		
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
}
