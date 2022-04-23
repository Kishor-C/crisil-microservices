package com.example.model.service;

import java.time.LocalDate;
import java.util.List;

import com.example.exceptions.EmployeeNotFoundException;
import com.example.model.beans.Employee;

public interface EmployeeService {
	// store employee
	public Employee storeEmployee(Employee employee);
	// get employee by id
	public Employee fetchEmployeeById(int id) throws EmployeeNotFoundException;
	// get all employees
	public List<Employee> fetchEmployees();
	// delete employee by id
	public void deleteEmployee(int id) throws EmployeeNotFoundException;
	// update employee dob based on id
	public Employee updateEmployeeDob(int id, LocalDate dob) throws EmployeeNotFoundException;
	// get all employees based on name
	public List<Employee> fetchEmployeesByName(String name) throws EmployeeNotFoundException;
}
