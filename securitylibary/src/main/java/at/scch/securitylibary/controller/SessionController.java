package at.scch.securitylibary.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.scch.securitylibary.util.AuthenticationResolver;
import at.scch.securitylibary.util.CustomAuthenticationEntity;

@RestController
public class SessionController {
	
	@GetMapping("/logout")
	public Object logout(HttpServletRequest request) throws Exception {
	    request.logout();
	    return new CustomAuthenticationEntity(null, null, null, null, false, null);
	}
	
	@RequestMapping("/authentication")
	public Object user() throws ServletException {
		return AuthenticationResolver.resolveCurrentAuthentication();
	}

}
