package com.example.demo.controller;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.authentication.CurrentAuthentication;
import com.example.demo.config.authentication.CustomAuthenticationEntity;

@RestController
public class SessionController {
	
	@GetMapping("/logout")
	public Object logout(HttpServletRequest request) throws Exception {
	    request.logout();
	    return new CustomAuthenticationEntity(null, null, null, null, false, null);
	}
	
	@RequestMapping("/authentication")
	public Object user(Principal principal, HttpServletRequest request, @CurrentAuthentication CustomAuthenticationEntity auth) throws ServletException {
		return auth;
	}

}
