package com.bank.sample.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bank.sample.model.CreditAccount;

public interface CreditAccountRepository extends CrudRepository<CreditAccount, Long> {

	@Query("select c from CreditAccount c where c.accountNumber = ?1 and c.customer.id =?2")
	public CreditAccount findByAccountNumberAndCustomer(String accountNumber , Long id);
}
