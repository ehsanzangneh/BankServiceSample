package com.bank.sample.exception;

public class BankServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1009262768273273853L;
	
	
	public BankServiceException() {}

	public BankServiceException(String message) {
		super(message);
	}

}
