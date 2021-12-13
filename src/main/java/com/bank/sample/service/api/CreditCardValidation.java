package com.bank.sample.service.api;

import java.util.function.BiFunction;

import com.bank.sample.model.CreditCard;

public enum CreditCardValidation {
	
	PIN( (card , pin) ->  card.getPinCode().equals(pin)) , 
	FINGER_PRINT((card , fingerPrint) -> card.getFingerPrint().equals(fingerPrint));
	private BiFunction<CreditCard, String, Boolean> function;
	
	private CreditCardValidation(BiFunction<CreditCard, String, Boolean> function) {
		this.function = function;
	}
	
	public boolean check(CreditCard creditCard,String code) {
		creditCard.setValidated(this.function.apply(creditCard, code));
		return creditCard.isValidated();
	}

}
