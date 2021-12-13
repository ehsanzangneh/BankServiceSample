package com.bank.sample.conroller.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class BankServiceInterceptor implements HandlerInterceptor{
	
	@Autowired
	private SessionContainer session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			String accept_language = request.getHeader("Accept-Language");
			Locale locale ;
			if(StringUtils.isNotBlank(accept_language))
				 locale = new Locale(accept_language);
			else locale = new Locale("en");
			session.setLocale(locale);			
			return true;
	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addInterceptor(new BankServiceInterceptor());
//	}

}
