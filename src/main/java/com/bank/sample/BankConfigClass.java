package com.bank.sample;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class BankConfigClass {

	//configuring ResourceBundle  
	@Bean  
	@Qualifier(value = "allMessages")
	public ResourceBundleMessageSource bundleMessageSource()  
	{  
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  
		messageSource.setBasename("exception_message"); 
		messageSource.addBasenames("messages");
		return messageSource;  
	} 
}
