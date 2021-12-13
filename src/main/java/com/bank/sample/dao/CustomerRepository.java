package com.bank.sample.dao;

import org.springframework.data.repository.CrudRepository;

import com.bank.sample.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	

}
