package com.Sutaruhin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
		Employee employee = new Employee();
		employee.setDeleteFlag(false);
		List <Employee> employees = repository.findAll(Example.of(employee));
		return employees;
	}


	public Optional<Employee> getEmployee(String id,Boolean delete_Flg) {
		return repository.findByCodeAndDeleteFlag(id,delete_Flg);
	}

//	public Optional<Employee> getDeletedEmployee(String id, Boolean delete_Flg){
//		return repository.findByCodeAndDeleteFlag(id,delete_Flg);
//	}


	@Transactional
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Transactional
	public void updateEmployee(String code, String name, Role role,String password,LocalDateTime updatedAt,boolean deleteFlag) {
		repository.employeeUpdate(code, name, role,password, updatedAt,deleteFlag);
	}
}
