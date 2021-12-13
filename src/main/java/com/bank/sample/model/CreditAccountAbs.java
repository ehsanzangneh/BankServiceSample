package com.bank.sample.model;

import com.bank.sample.model.api.Account;

public abstract class CreditAccountAbs implements Account{

	@Override
	public int hashCode() {
		return this.getAccountNumber().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CreditAccountAbs) {
			CreditAccountAbs that = (CreditAccountAbs)obj;
			return this.getAccountNumber().equals(that.getAccountNumber());
		}
		return false;
	}
	
}
