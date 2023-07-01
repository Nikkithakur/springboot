package com.microservices.exception;

public class AccountNotPresentException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccountNotPresentException() {
		super();
	}

	public AccountNotPresentException(String errorMessage) {
		super(errorMessage);
	}

}
