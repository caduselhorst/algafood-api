package com.algaworks.algafood.infrastructure.service.email;

public class EmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4723125614731323149L;

	public EmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailException(String message) {
		super(message);
	}

	
	
}
