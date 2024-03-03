//package com.Sutaruhin.service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.Sutaruhin.entity.Employee;
//
//public class UserDetail implements UserDetails {
//
//	private static final long serialVersionUID = 1L;
//	private final Employee employee;
//	private final Collection<? extends GrantedAuthority> authorities;
//
//
//	public UserDetail(Employee employee) {
//		this.employee=employee;
//		this.authorities = new ArrayList<GrantedAuthority>();
//	}
//
//
//
//	public Employee getEmployee() {
//		return employee;
//	}
//
//
//	public Collection<? extends GrantedAuthority> getAuthorities(){
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return employee.getPassword();
//	}
//
//
//	@Override
//	public String getUsername() {
//		return employee.getName();
//	}
//
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//}
