package at.scch.patientservice.config;

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
		
			case "PatientViewPolicy":
				if(AuthorityChecker.hasAuthority(auth, "SEE_SENSITIV_DATA")) {
					return Views.Detail.class;
				}
				return Views.Simple.class;
		
		}
		return null;	
	}

}
