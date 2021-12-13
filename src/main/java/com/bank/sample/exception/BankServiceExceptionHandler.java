package com.bank.sample.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.sample.conroller.util.SessionContainer;

@ControllerAdvice
public class BankServiceExceptionHandler {
	
	@Autowired
	@Qualifier("allMessages")
	private ResourceBundleMessageSource resourceBundleMessageSource;
	
	@Autowired
	private SessionContainer session;
	
	@ExceptionHandler(value = CardValidationRequired.class)
	public ResponseEntity<String> cardValidationRequired(CardValidationRequired ex){		
		String message = resourceBundleMessageSource.getMessage("card.validation.required", null, session.getLocale());
		return new ResponseEntity<String>(message, HttpStatus.FORBIDDEN);
		
	}
	
	@ExceptionHandler(value = BankServiceException.class)
	public ResponseEntity<String> bankServiceExceptionHandler(BankServiceException ex){
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
