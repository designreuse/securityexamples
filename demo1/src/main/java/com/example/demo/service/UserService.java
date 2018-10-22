package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.User;

public interface UserService extends UserDetailsService{
	
	@Override
	User loadUserByUsername(String username) throws UsernameNotFoundException;
	
	@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	Collection<User> getAllUsers();

}
