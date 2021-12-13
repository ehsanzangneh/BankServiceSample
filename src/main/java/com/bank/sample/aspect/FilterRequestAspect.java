package com.bank.sample.aspect;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bank.sample.conroller.util.SessionContainer;

@Aspect
@Component
public class FilterRequestAspect {

	@Autowired
	private SessionContainer session;
	
	@Before(value = "@annotation(com.bank.sample.aspect.annotations.FilterRequest)||@within(com.bank.sample.aspect.annotations.FilterRequest)" )
	public void getLanguage() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String accept_language = request.getHeader("Accept-Language");
		Locale locale ;
		if(StringUtils.isNotBlank(accept_language))
			 locale = new Locale(accept_language);
		else locale = new Locale("en");
		session.setLocale(locale);		
//		System.out.println("HEEEEEELLLLLLLLLLLLLLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
	}
}
