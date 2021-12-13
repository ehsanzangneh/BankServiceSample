package com.bank.sample.conroller;


import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.sample.aspect.annotations.FilterRequest;
import com.bank.sample.conroller.util.SessionContainer;
import com.bank.sample.model.CreditCard;
import com.bank.sample.service.api.*;
import static com.bank.sample.service.api.BankServiceMessages.*;

@RestController
@FilterRequest
@RequestMapping(value = "/creditcard")
public class CreditCardRest {

	@Autowired
	SessionContainer sessionContainer;
	
	@Autowired
	private CreditCardService service;
	

	
	@PostMapping(value = "checkcardnumber")
	public ResponseEntity<String> fetchByNumber(@RequestBody Map<String, String> cardNumber){
		Optional<CreditCard> creditCard = service.getByCardNumber(cardNumber.get("cardNumber"));
		creditCard.ifPresent(a -> {sessionContainer.setCreditCard(a);});
		String resultMess = creditCard.isPresent()? CREDIT_CARD_IS_FOUND : CREDIT_CARD_DOES_NOT_EXIST;
		return new ResponseEntity<String>(resultMess,HttpStatus.OK);		
	}
	
	@PostMapping(value = "pinauth")
	public ResponseEntity<String> pinAuth(@RequestBody Map<String, String> param){
		Optional<CreditCard> optCard = sessionContainer.getCreditCard();
		String message = this.service.validation(optCard, param.get("pinCodeMap"), CreditCardValidation.PIN);
		return new ResponseEntity<String>(message, HttpStatus.OK);		
	}
	@PostMapping(value = "fingerauth")
	public ResponseEntity<String> fingerAuth(@RequestBody Map<String, String> param){
		Optional<CreditCard> optCard = sessionContainer.getCreditCard();
		String message = this.service.validation(optCard, param.get("fingerCodeMap"), CreditCardValidation.FINGER_PRINT);
		return new ResponseEntity<String>(message, HttpStatus.OK);		
	}
	
	@PostMapping(value = "balance")
	public ResponseEntity<Map<String, Object>> checkBalance(){
		Optional<CreditCard> optCard = sessionContainer.getCreditCard();
		Map<String, Object> resultMap = service.checkBalance(optCard);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@PostMapping(value = "deposit")
	public ResponseEntity<Map<String, Object>> deposit(@RequestBody Map<String, String> param){
		Optional<CreditCard> optCard = sessionContainer.getCreditCard();
		Map<String, Object> result = service.deposit(optCard, param.get("amount"));
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "withdraw")
	public ResponseEntity<Map<String, Object>> withdraw(@RequestBody Map<String, String> param){
		Optional<CreditCard> optCard = sessionContainer.getCreditCard();
		Map<String, Object> result = service.withdraw(optCard, param.get("amount"));
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
}
