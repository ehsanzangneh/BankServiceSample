package com.bank.sample.model;

import com.bank.sample.model.api.CustomerInt;

public abstract class CustomerAbs implements CustomerInt{
	
	@Override
	public int hashCode() {
		return this.getUniqueCode().hashCode() ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CustomerInt) {
			CustomerInt that = (CustomerInt) obj;
			return this.getUniqueCode().equals(that.getUniqueCode());
		}
		return false;
	}

}
