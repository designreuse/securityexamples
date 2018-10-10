package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.View;
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
	
	/*@RequestMapping("/api/users")
	public MappingJacksonValue getFoo(@AuthenticationPrincipal UserDetails userDetails ) {
	  MappingJacksonValue value = new MappingJacksonValue( fooService.getAll() );
	  if( userDetails.isAdminUser() ) {
	    value.setSerializationView( View.Admin.class );
	  } else {
	    value.setSerializationView( View.User.class );
	  }
	  return value;
	}*/

	
	

}
