package com.example.demo.config;

import java.lang.annotation.Annotation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.View;
import com.example.demo.util.AuthorityChecker;

@Component
public class JsonViewCalculator {
	
	public Class<?> getJSONView(Object responseValue){
		
		String policy = getViewPolicy(responseValue);
		if(policy == null) {
			return null;
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		switch(policy) {
			case "MedicalViewPolicy":
				if(AuthorityChecker.hasAuthority(authentication, "READ_SENSITIVE_PATIENT_DATA")) {
					return View.Detail.class;
				}
				if(AuthorityChecker.hasAuthority(authentication, "READ_LABORITORY_RESULTS")) {
					return View.Extended.class;
				}
				return View.Simple.class;
			
		}
		return null;
		
		
	}

	private String getViewPolicy(Object responseValue) {
		if(responseValue==null) {
			return null;
		}
		
		Class<?> responseTyp = getResponseType(responseValue);
		for(Annotation a: responseTyp.getAnnotations()) {
			if(a instanceof SecuredEntityViewPolicy) {
				return ((SecuredEntityViewPolicy)a).value();
			}
		}
		return null;
	}

	private Class<?> getResponseType(Object responseValue) {
		if(!(responseValue instanceof Iterable)) {
			return responseValue.getClass();
		}
		Iterable<?> list = (Iterable<?>)responseValue;
		if(list.iterator().hasNext()) {
			return list.iterator().next().getClass();
		}
		return null;
	}

}
