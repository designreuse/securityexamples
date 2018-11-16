package at.scch.securitylibary.util;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthorityChecker {
	
	public static boolean hasAuthority (Authentication authentication, String authority) {
		if (authentication != null	&& authentication.getAuthorities() != null) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			if (authorities.stream().anyMatch(o -> o.getAuthority().equals(authority))) {
				return true;
			}
		}		
		return false;	
	}

}
