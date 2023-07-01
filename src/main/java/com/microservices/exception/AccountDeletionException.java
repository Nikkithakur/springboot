package com.microservices.exception;

public class AccountDeletionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccountDeletionException() {
		super();
	}

	public AccountDeletionException(String errorMessage) {
		super(errorMessage);
	}

}

