package com.bank.sample.service.api;

import com.bank.sample.model.Customer;

public interface CustomerService {
	public Customer save(Customer customer);
	public boolean hasCreditAccount(String accountNumber , Long customerId);
	
}
