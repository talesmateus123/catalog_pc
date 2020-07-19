/** 
 * This is the "ResourceExceptionHandler" class. Which will be able to provide all system exception handling.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pml.services.exceptions.AuthorizationException;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.IllegalArgException;
import com.pml.services.exceptions.ObjectNotFoundException;
import com.pml.services.exceptions.InvalidQueryException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		// The Http status
		HttpStatus status = HttpStatus.NOT_FOUND;
		// The standard error
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Not Found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegity(DataIntegrityException e, HttpServletRequest request){
		// The Http status
		HttpStatus status = HttpStatus.BAD_REQUEST;
		// The standard error
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Data Integrity", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ConflictOfObjectsException.class)
	public ResponseEntity<StandardError> ConflictOfObjects(ConflictOfObjectsException e, HttpServletRequest request){
		// The Http status
		HttpStatus status = HttpStatus.CONFLICT;
		// The standard error
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Conflict of Objects", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> ValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
		// The Http status
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		// The validation error
		ValidationError err = new ValidationError(System.currentTimeMillis(), status.value(), "Validation Error", e.getMessage(), request.getRequestURI());	
		for(FieldError fieldError : e.getBindingResult().getFieldErrors())
			err.addError(fieldError.getField(), fieldError.getDefaultMessage());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorizationException(AuthorizationException e, HttpServletRequest request){
		// The Http status
		HttpStatus status = HttpStatus.FORBIDDEN;
		// The standard error
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Access Denied", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(InvalidQueryException.class)
	public ResponseEntity<StandardError> invalidQueryException(InvalidQueryException e, HttpServletRequest request){
		// The Http status
		HttpStatus status = HttpStatus.BAD_REQUEST;
		// The standard error
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Bad Request", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(IllegalArgException.class)
	public ResponseEntity<StandardError> illegalArgumentException(IllegalArgException e, HttpServletRequest request){
		// The Http status
		HttpStatus status = HttpStatus.BAD_REQUEST;
		// The standard error
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Bad Request", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	
	
}
