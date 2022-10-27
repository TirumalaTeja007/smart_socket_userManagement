package com.sana.apple.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ResourceRegistrationException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String resourceName;
	private final String message;

	public ResourceRegistrationException(String resourceName, String message) {
		super(String.format("Failed to register Reource[%s] : '%s'", resourceName, message));
		this.resourceName = resourceName;
		this.message = message;
	}

}
