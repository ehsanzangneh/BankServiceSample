package com.bank.sample.service;

import static com.bank.sample.service.api.BankServiceMessages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.sample.dao.CreditAccountRepository;
import com.bank.sample.dao.CreditCardRepository;
import com.bank.sample.exception.CardValidationRequired;
import com.bank.sample.model.CreditCard;
import com.bank.sample.service.api.CreditCardService;
import com.bank.sample.service.api.CreditCardValidation;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private CreditAccountRepository creditAccountRepository;
	

	@Override	
	public String validation( Optional<CreditCard> card , String passCode, CreditCardValidation validation ) {
		if(card.isPresent()) {
			if(card.get().isBlocked())return CREDIT_CARD_IS_BLOCKED;
			
			if(validation.check(card.get(), passCode)) {
				card.get().resetNumberOfAttempts();
				return CREDIT_CARD_IS_VALID;
			}
			card.get().increaseNumberOfAttempts();
			creditCardRepository.save(card.get());
		}
		
		return CREDIT_CARD_VALIDATION_FAILED;
	}


	@Override
	public Optional<CreditCard> getByCardNumber(String Number) {
		Optional<CreditCard> result = this.creditCardRepository.findBycreditCardNumber(Number);
		return result;
		
	}


	@Override
	public CreditCard save(CreditCard card) {
		return this.creditCardRepository.save(card);
	}


	@Override
	public Map<String, Object> checkBalance(Optional<CreditCard> card) {
		Map<String,Object> result = new HashMap<String, Object>();
		if(card.isPresent() && card.get().isValidated()) {			
			result.put(RESULT, card.get().getCreditAccount().getBalance());			
		}else {
			throw new CardValidationRequired();
		}
		return result;
	}


	@Override
	public Map<String, Object> deposit(Optional<CreditCard> card, String amount) {
		Map<String,Object> result = new HashMap<String, Object>();
		if(card.isPresent() && card.get().isValidated()) {
			double amountDouble = NumberUtils.isCreatable(amount)? Double.parseDouble(amount):0;
			double balance = card.get().getCreditAccount().deposit(amountDouble);
			creditAccountRepository.save(card.get().getCreditAccount());
			result.put(MESSAGE, DEPOSIT_DONE_SUCCESSFULLY);
			result.put(RESULT, balance);
			
		}else {
			throw new CardValidationRequired();
		}
		return result;
	}


	@Override
	public Map<String, Object> withdraw(Optional<CreditCard> card, String amount) {
		Map<String,Object> result = new HashMap<String, Object>();
		if(card.isPresent() && card.get().isValidated()) {
			double amountDouble = NumberUtils.isCreatable(amount)? Double.parseDouble(amount):0;
			double balance = card.get().getCreditAccount().withdraw(amountDouble);
			creditAccountRepository.save(card.get().getCreditAccount());
			result.put(MESSAGE, WITHDRAW_DONE_SUCCESSFULLY);
			result.put(RESULT, balance);
			
		}else {
			throw new CardValidationRequired();
		}
		return result;
	}


	

	
	
	

}
