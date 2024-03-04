package com.Sutaruhin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Sutaruhin.entity.Employee;

public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final Employee employee;
	private final List<SimpleGrantedAuthority> authorities;


	public UserDetail(Employee employee) {
		this.employee=employee;
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(employee.getRole().toString()));
		this.authorities = authorities;

	}



	public Employee getEmployee() {
		return employee;
	}


	public List<SimpleGrantedAuthority> getAuthorities(){
		return authorities;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}


	@Override
	public String getUsername() {
		return employee.getName();
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}
}
