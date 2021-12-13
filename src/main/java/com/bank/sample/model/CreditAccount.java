package com.bank.sample.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
public class CreditAccount extends CreditAccountAbs{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "AccountNumber is not provided!")
	@Column(unique = true)
	private String accountNumber;
	
	private double balance;
	
	@OneToOne(mappedBy = "creditAccount")
	private CreditCard creditCard;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	private Customer customer;
	
	public double deposit(double amount) {
		this.balance += amount;
		return this.balance;
	}
	
	public double withdraw(double amount) {
		this.balance -= amount;
		return this.balance;
	}
	
	public CreditAccount(String accountNumber, double balance, Customer customer) {
		this.customer = customer;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

}
