package com.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.beans.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	// EmployeeDao inherits all the methods of JpaRepository & then operation on employee table
	@Query("select e from Employee e where e.name = ?1")
	public List<Employee> findEmployeesByName(String name);
}
