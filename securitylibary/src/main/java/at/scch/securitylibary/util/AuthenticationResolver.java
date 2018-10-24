package at.scch.securitylibary.util;

import java.util.HashSet;
import java.util.Set;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationResolver {
	
	public static CustomAuthenticationEntity resolveCurrentAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof KeycloakAuthenticationToken)) {
			return null;
		}
		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) auth;
		KeycloakPrincipal<?> principal=(KeycloakPrincipal<?>)token.getPrincipal();
        AccessToken accessToken = principal.getKeycloakSecurityContext().getToken();
        Set<String> authorities = new HashSet<>();
        token.getAuthorities().forEach(a->{
        	authorities.add(a.toString());
        });

		return new CustomAuthenticationEntity(accessToken.getId(), accessToken.getGivenName(), accessToken.getFamilyName(), accessToken.getEmail(), true,
				authorities);
		
	}

}
