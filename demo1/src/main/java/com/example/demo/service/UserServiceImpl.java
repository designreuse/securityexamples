package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private List<User> users;
	
	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder) {
		users = new ArrayList<>();
		users.add(new User("simpleuser", passwordEncoder.encode("password"), new String[]{"ROLE_USER"}));
		users.add(new User("admin", passwordEncoder.encode("password"), new String[]{"ROLE_USER", "ROLE_ADMIN"}));
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		for(User u : users) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		throw new UsernameNotFoundException(username);
	}

}
