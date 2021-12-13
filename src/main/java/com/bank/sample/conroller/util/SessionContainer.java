package com.bank.sample.conroller.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.bank.sample.model.CreditCard;

@SessionScope
@Component
public class SessionContainer {
	private Map<String, Object> sessionMap = new HashMap<String, Object>();
	private static final String CREDIT_CARD_KEY= "SC_creditCard";
	private static final String REQUEST_LOCALE = "SC_LOCALE";

	public Object getAttribute(String key) {
		return sessionMap.get(key);
	}

	public void setAttribute(String key , Object value) {
		this.sessionMap.put(key, value);		
	}
	
	public void setCreditCard(CreditCard creditCard) {
		this.setAttribute(SessionContainer.CREDIT_CARD_KEY, creditCard);
	}
	
	public Optional<CreditCard> getCreditCard() { 
		Object value = this.getAttribute(SessionContainer.CREDIT_CARD_KEY);
		
		return (value != null && value instanceof CreditCard) ?
							Optional.of((CreditCard)value):Optional.ofNullable(null);
	}
	
	public void setLocale(Locale locale) {
		this.sessionMap.put(REQUEST_LOCALE, locale);
	}
	
	public Locale getLocale() {
		Object value = this.sessionMap.get(REQUEST_LOCALE);
		return (value instanceof Locale) ? (Locale) value : null;
	}
	
	
}
