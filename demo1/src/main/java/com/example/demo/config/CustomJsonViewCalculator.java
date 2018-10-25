package com.example.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import at.scch.securitylibary.config.JsonViewCalculator;
import at.scch.securitylibary.config.Views;
import at.scch.securitylibary.util.AuthorityChecker;

@Component
public class CustomJsonViewCalculator extends JsonViewCalculator{

	@Override
	protected Class<?> getJsonView(String viewPolicy, Object responseValue, Authentication auth) {
		switch(viewPolicy) {
		
			case "MedicalViewPolicy":
				if(AuthorityChecker.hasAuthority(auth, "READ_EXTENDED_LABORATORY_RESULTS")) {
					return Views.Extended.class;
				}
				return Views.Simple.class;
		
		}
		return null;	
	}

}
