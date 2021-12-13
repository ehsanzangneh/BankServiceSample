package com.bank.sample.conroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.sample.aspect.annotations.FilterRequest;
import com.bank.sample.dao.CreditAccountRepository;
import com.bank.sample.dao.CreditCardRepository;
import com.bank.sample.dao.CustomerRepository;
import com.bank.sample.model.CreditAccount;
import com.bank.sample.model.CreditCard;
import com.bank.sample.model.Customer;

@RestController
@FilterRequest
@RequestMapping("/testrest")
public class TestController {
private static List<Customer> customers = new ArrayList<>();
private static List<CreditAccount> accounts = new ArrayList<>();
private static List<CreditCard> cards = new ArrayList<CreditCard>();
	
	static {
		Customer cus = new Customer("Sam", "Rostami", "23589");
		CreditAccount account = new CreditAccount("256872589", 2000, cus);
		customers.add(cus);accounts.add(account);
		cards.add(new CreditCard("120", "1568", "kkkk", account, cus));
		
		cus = new Customer("Mohsen", "Rezaee", "14895");
		account = new CreditAccount("23845929", 500.25, cus);
		customers.add(cus);accounts.add(account);
		cards.add(new CreditCard("130", "42568", "ppppp", account, cus));
		cus = new Customer("Sara", "Kiani", "42366");
		account = new CreditAccount("26823582", 1250.36, cus);
		customers.add(cus);accounts.add(account);
		cards.add(new CreditCard("140", "25869", "gggggg", account, cus));
		
	}
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CreditAccountRepository creditAccountRepository;
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	@Qualifier("allMessages")
	private ResourceBundleMessageSource resourceBundleMessageSource;
	

	
	@GetMapping(value = "/setDataBase")
	public void bulkSave() {
		customers.forEach(a -> customerRepository.save(a));
		accounts.forEach(a-> creditAccountRepository.save(a));
		cards.forEach(a-> creditCardRepository.save(a));
	}
	@GetMapping(value = "/hello")
	public String hello(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return resourceBundleMessageSource.getMessage("greetings", null, locale);
	}

}
