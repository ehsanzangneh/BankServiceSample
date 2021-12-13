package com.bank.sample.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CardValidationRequired extends BankServiceException{

	public CardValidationRequired(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
