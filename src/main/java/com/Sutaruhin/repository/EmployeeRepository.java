package com.Sutaruhin.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.Sutaruhin.entity.Employee;
import com.Sutaruhin.entity.Employee.Role;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Optional<Employee> findByName(String name);

	@Modifying
	@Query(value = "UPDATE Employee e SET e.name = :name,e.role = :role, e.password = :password, e.updatedAt = :updatedAt where e.code = :code")
	public void employeeUpdate(@Param("code")String code,@Param("name") String name,@Param("role")Role role, @Param("password") String password,@Param("updatedAt") LocalDateTime updatedAt);
//	public void employeeUpdate(@Param("code")String code,@Param("name") String name);

//	@Query(value = "SELECT e FROM Employee e")
//	Optional<Employee> employeeUpdate(@Param("code")String code,@Param("name") String name,@Param("password") String password,@Param("updatedAt") LocalDateTime updatedAt);



}
