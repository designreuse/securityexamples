package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value= "api/users/{username}", method = RequestMethod.GET)
	public User loadUserByUsername(@PathVariable String username) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return userService.loadUserByUsername(username);
	}	

}
