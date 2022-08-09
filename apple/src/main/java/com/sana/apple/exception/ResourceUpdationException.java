package com.sana.apple.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ResourceUpdationException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String name;
	private final String message;

	public ResourceUpdationException(String name, String message) {
		super(String.format("Failed to update reource[%s] : '%s'", name, message));
		this.name = name;
		this.message = message;
	}

}
