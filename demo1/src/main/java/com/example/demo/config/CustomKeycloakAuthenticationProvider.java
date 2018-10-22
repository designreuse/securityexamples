package com.example.demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.keycloak.adapters.springsecurity.account.KeycloakRole;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

public class CustomKeycloakAuthenticationProvider extends KeycloakAuthenticationProvider {

	private GrantedAuthoritiesMapper grantedAuthoritiesMapper;

	public void setGrantedAuthoritiesMapper(GrantedAuthoritiesMapper grantedAuthoritiesMapper) {
		this.grantedAuthoritiesMapper = grantedAuthoritiesMapper;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		try {
			for (String role : token.getAccount().getKeycloakSecurityContext().getToken().getRealmAccess().getRoles()) {
				grantedAuthorities.add(new KeycloakRole(role));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (String role : token.getAccount().getRoles()) {
			grantedAuthorities.add(new KeycloakRole(role));
		}
		return new KeycloakAuthenticationToken(token.getAccount(), token.isInteractive(),
				mapAuthorities(grantedAuthorities));
	}

	private Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
		return grantedAuthoritiesMapper != null ? grantedAuthoritiesMapper.mapAuthorities(authorities) : authorities;
	}

}
