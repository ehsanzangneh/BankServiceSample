package com.bank.sample.service.api;

import java.util.Map;
import java.util.Optional;

import com.bank.sample.model.CreditCard;

public interface CreditCardService {
	
	public Optional<CreditCard> getByCardNumber(String Number);
	public String validation(Optional<CreditCard> card, String passCode,CreditCardValidation validation);
	public CreditCard save(CreditCard card);
	public Map<String, Object> checkBalance(Optional<CreditCard> card);
	public Map<String, Object> deposit(Optional<CreditCard> card , String amount);
	public Map<String, Object> withdraw(Optional<CreditCard> card , String amount);

}
