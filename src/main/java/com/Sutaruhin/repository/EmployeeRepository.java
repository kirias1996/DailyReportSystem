package com.Sutaruhin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sutaruhin.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

//	Optional<Employee> findByName(String name);

}
