package com.example.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;

@RestController
public class UserController {
	
	@RequestMapping(value= "api/users/{id}", method = RequestMethod.GET)
	@Secured("ROLE_User")
	public User getUserById(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = new User();
		user.setId(1l);
		user.setUsername("user");
		user.setPassword("password");
		user.getPassword();
		return user;
	}

}
