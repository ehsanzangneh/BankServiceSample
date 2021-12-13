package com.bank.sample.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Customer extends CustomerAbs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String family;
	
	@Column(unique = true)
	private String nationalId;
	
	
	public Customer(String name , String family , String nationalId) {
		this.family = family;
		this.name = name;
		this.nationalId = nationalId;
	}
	
	@OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)	
	private Set<CreditCard> creditCards;
	
	@OneToMany(mappedBy = "customer" , fetch = FetchType.LAZY)
	private Set<CreditAccount> creditAccounts;


	@Override
	public String getUniqueCode() {
		return this.getNationalId();
	}

}
