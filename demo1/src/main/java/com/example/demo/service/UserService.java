package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.User;

public interface UserService extends UserDetailsService{
	
	@Override
	User loadUserByUsername(String username) throws UsernameNotFoundException;

}
