package com.sana.apple.exception;

public class BadCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadCredentialsException(String message) {
		super(message);
	}

	public BadCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}

}
