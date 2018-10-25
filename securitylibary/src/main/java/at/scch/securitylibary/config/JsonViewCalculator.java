package at.scch.securitylibary.config;

import java.lang.annotation.Annotation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class JsonViewCalculator {
	
	protected Class<?> getJsonView(Object responseValue){
		
		String policy = getViewPolicy(responseValue);
		if(policy == null) {
			return null;
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return getJsonView(policy, responseValue, authentication);	
	}
	
	protected abstract Class<?> getJsonView(String viewPolicy, final Object responseValue, final Authentication auth);

	protected String getViewPolicy(Object responseValue) {
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

	protected Class<?> getResponseType(Object responseValue) {
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
