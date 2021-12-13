package com.bank.sample.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.sample.dao.CreditAccountRepository;
import com.bank.sample.dao.CustomerRepository;
import com.bank.sample.model.CreditAccount;
import com.bank.sample.model.Customer;
import com.bank.sample.service.api.CustomerService;

public class CustomerImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CreditAccountRepository creditAccountRepository;

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public boolean hasCreditAccount(String accountNumber, Long customerId) {
		CreditAccount account = creditAccountRepository.findByAccountNumberAndCustomer(accountNumber, customerId);
		return account != null;
	}

}
