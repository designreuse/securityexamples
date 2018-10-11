package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
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
		users.add(new User("patient1", passwordEncoder.encode("password"), new String[]{"ROLE_USER", "ROLE_PATIENT"}));
		users.add(new User("patient2", passwordEncoder.encode("password"), new String[]{"ROLE_USER", "ROLE_PATIENT"}));
		users.add(new User("patient3", passwordEncoder.encode("password"), new String[]{"ROLE_USER", "ROLE_PATIENT"}));
		users.add(new User("admin", passwordEncoder.encode("password"), new String[]{"ROLE_USER", "ROLE_ADMIN"}));
		users.add(new User("doctor1", passwordEncoder.encode("password"), new String[]{"ROLE_USER", "ROLE_DOCTOR", "READ_LABORITORY_RESULTS", "READ_SENSITIVE_PATIENT_DATA"}));
		users.add(new User("doctor2", passwordEncoder.encode("password"), new String[]{"ROLE_USER", "ROLE_DOCTOR", "READ_LABORITORY_RESULTS"}));
		
		//Mapping ROLE_DOCTOR --> READ_LABORITORY_RESULTS
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

	@Override
	public Collection<User> getAllUsers() {
		return users;
	}

}
