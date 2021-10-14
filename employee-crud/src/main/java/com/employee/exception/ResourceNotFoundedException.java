package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2299260847850477591L;

	
	public ResourceNotFoundedException(String message) {
		super(message);
	}
}
