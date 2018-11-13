package at.scch.laboratoryservice.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import at.scch.securitylibary.config.jsonview.JsonViewCalculator;
import at.scch.securitylibary.config.jsonview.Views;
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
