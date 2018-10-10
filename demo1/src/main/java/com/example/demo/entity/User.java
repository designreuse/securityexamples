package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;

public class User implements UserDetails{
	
	private static final long serialVersionUID = -2395355081760067661L;
	
	@JsonView(View.Anonymous.class)
	private String username;
	
	@JsonView(View.Admin.class)
	private String password;
	
	private Set<UserAuthority> authorities;
	
	public User() {
		super();
	}
	
	public User(String username, String password, String[] authorities) {
		this.username = username;
		this.password = password;
		this.authorities = new HashSet<>();
		for(String a:authorities) {
			this.authorities.add(new UserAuthority(a));
		}
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAuthorities(Set<UserAuthority> authorities) {
		this.authorities = authorities;
	}
	@Override
	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
