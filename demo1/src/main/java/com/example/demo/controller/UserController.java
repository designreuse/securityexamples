package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.View;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "api/users/{username}", method = RequestMethod.GET)
	public MappingJacksonValue loadUserByUsername(@PathVariable String username, @AuthenticationPrincipal UserDetails user) {
		
		MappingJacksonValue value = new MappingJacksonValue(userService.loadUserByUsername(username));
		if (user instanceof User && ( ((User)user).getUsername().equals(username) || ((User)user).isAdminUser()) ){
			value.setSerializationView(View.Admin.class);
		} else {
			value.setSerializationView(View.User.class);
		}
		return value;
	}

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public Collection<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@JsonView(View.Admin.class)
	@RequestMapping(value = "api/users/{username}/full", method = RequestMethod.GET)
	public User loadfullUserByUsername(@PathVariable String username) {
		return userService.loadUserByUsername(username);
	}
	

}
