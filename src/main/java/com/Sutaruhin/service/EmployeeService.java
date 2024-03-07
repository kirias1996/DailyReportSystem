package com.Sutaruhin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Sutaruhin.entity.Employee;
import com.Sutaruhin.entity.Employee.Role;
import com.Sutaruhin.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public List<Employee> getEmployeeList(){
		List <Employee> employees = repository.findAll();
		return employees;
	}


	public Optional<Employee> getEmployee(String id) {
		return repository.findById(id);
	}


	@Transactional
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Transactional
	public void updateEmployee(String code, String name, Role role,String password,LocalDateTime updatedAt) {
		repository.employeeUpdate(code, name, role,password, updatedAt);
	}
}
