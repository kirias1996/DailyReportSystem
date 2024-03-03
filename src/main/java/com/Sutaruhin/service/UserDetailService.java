//package com.Sutaruhin.service;
//
//import java.util.Optional;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import com.Sutaruhin.entity.Employee;
//import com.Sutaruhin.repository.EmployeeRepository;
//
//@Service
//public class UserDetailService implements UserDetailsService {
//	private final EmployeeRepository employeeRepository;
//
//	public UserDetailService(EmployeeRepository repository) {
//		this.employeeRepository = repository;
//	}
//	@Override
//	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//		Optional<Employee> employee =employeeRepository.findByName(name);
//
//
////		if(authentication.isEmpty()) {
////
////		}
//		return new UserDetail(employee.get());
//	}
//
//}
