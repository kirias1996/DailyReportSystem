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

//	Optional<Employee> existsByCodeAndDelete_flag(String id, boolean delete_Flg);

	@Modifying
	@Query(value = "UPDATE Employee e SET e.name = :name,e.role = :role, e.password = :password, e.updatedAt = :updatedAt,e.deleteFlag = :deleteFlag where e.code = :code")
	public void employeeUpdate(@Param("code")String code,@Param("name") String name,@Param("role")Role role, @Param("password") String password,@Param("updatedAt") LocalDateTime updatedAt,@Param("deleteFlag") boolean deleteFlag);

	Optional<Employee> findByCodeAndDeleteFlag(String id, Boolean delete_Flg);



}
