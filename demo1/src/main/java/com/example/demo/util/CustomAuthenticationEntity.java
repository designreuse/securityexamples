package com.example.demo.util;

import java.util.Set;

public class CustomAuthenticationEntity {

	private String id;
	
	private String email;

	private String firstName;

	private String lastName;
	
	private boolean authenticated;

	private Set<String> roles;
	
	public CustomAuthenticationEntity(String id, String firstName, String lastName, String email, boolean authenticated, Set<String> roles) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
		this.authenticated = authenticated;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public String getId() {
		return id;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}
}
