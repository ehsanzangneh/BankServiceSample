package com.bank.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class CreditCard extends CreditAccountAbs{
	private static final int MAXIMUM_NUMBER_OF_ATTEMPS = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "card number is required!")
	@Column(unique = true)
	private String creditCardNumber;
	
	@NotBlank(message = "pincode is required!")
	private String pinCode;
	
	private String fingerPrint;
	
	private boolean blocked;
	
	@OneToOne
	@JoinColumn(name = "account_id")
	@NotNull(message = "Account is null!")
	private CreditAccount creditAccount;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@NotNull(message = "customer is null!")
	private Customer customer;
	
	@Transient
	private boolean validated;
	
	@Setter(value = AccessLevel.NONE)
	private int numberOfValidationAttempts;
	
	public void increaseNumberOfAttempts() {
		this.numberOfValidationAttempts++;
		if(this.numberOfValidationAttempts >= CreditCard.MAXIMUM_NUMBER_OF_ATTEMPS) {
			this.validated = false;
			this.blocked = true;
		}
	}
	public void resetNumberOfAttempts() {
		this.numberOfValidationAttempts = 0;
	}

	@Override
	public String getAccountNumber() {
		return this.getCreditCardNumber();
	}

	public CreditCard(String creditCardNumber, String pinCode, 
			String fingerPrint,CreditAccount creditAccount , Customer customer) {
		this.creditCardNumber = creditCardNumber;
		this.creditAccount = creditAccount;
		this.pinCode = pinCode;
		this.fingerPrint = fingerPrint;
		this.creditAccount = creditAccount;
		this.customer = customer;
	}


}
