package com.bank.sample.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bank.sample.model.CreditCard;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
	
	public Optional<CreditCard> findBycreditCardNumber(String creditCardNumber);

}
