package com.bank.sample.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.sample.dao.CustomerRepository;

@RestController
@RequestMapping("/cutomer")
public class CustomerRest {
	@Autowired
	private CustomerRepository customerRepository;
	
}
