package com.bank.sample.model;

import com.bank.sample.model.api.Card;

public abstract class CreditCardAbs implements Card{
	
	@Override
	public int hashCode() {
		return this.getCardNumber().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CreditAccountAbs) {
			CreditAccountAbs that = (CreditAccountAbs)obj;
			 return this.getCardNumber().equals(that.getAccountNumber());
		}
		return false;
	}

}
