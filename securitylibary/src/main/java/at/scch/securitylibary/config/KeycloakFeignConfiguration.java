package at.scch.securitylibary.config;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;

//Configuration //Apply this configuration to all feign clients
public class KeycloakFeignConfiguration{
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	@Bean
	public RequestInterceptor keycloakInterceptor() {
		//return new KeycloakSecurityContextClientRequestInterceptor();
		return new RequestInterceptor() {

			@Override
			public void apply(RequestTemplate template) {
				KeycloakSecurityContext context = getKeycloakSecurityContext();
				if(context!=null) {
					template.header(AUTHORIZATION_HEADER, "Bearer " + context.getTokenString());
				}
			}
			
		};
	}
	
    /**
     * Returns the {@link KeycloakSecurityContext} from the Spring {@link SecurityContextHolder}'s {@link Authentication}.
     *
     * @return the current <code>KeycloakSecurityContext</code>
     */
	protected KeycloakSecurityContext getKeycloakSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakAuthenticationToken token;
        KeycloakSecurityContext context;

        if (authentication == null) {
            return null;
        }

        if (!KeycloakAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
        	return null;
        }

        token = (KeycloakAuthenticationToken) authentication;
        context = token.getAccount().getKeycloakSecurityContext();

        return context;
    }

}
