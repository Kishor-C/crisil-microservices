package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.EmployeeNotFoundException;
import com.example.model.beans.Employee;
import com.example.model.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeRest {

	// you need to use EmployeeService
	@Autowired
	private EmployeeService service;
	// store employee
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> store(@RequestBody Employee emp) {
		Employee e = service.storeEmployee(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(e);
	}
	// retrieve all employees
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> fetch() {
		List<Employee> list = service.fetchEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	// retrieve employee by id
	@GetMapping(path = "{empId}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Object> fetchEmployee(@PathVariable("empId") int id) {
		try {
			Employee e = service.fetchEmployeeById(id);
			return ResponseEntity.status(HttpStatus.OK).body(e);
		} catch (EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": "+"\""+e.getMessage()+"\""+"}");
		}
	}
	//update employee dob by id
	@PutMapping(path = "{empId}/{empDob}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateEmployeeDob(@PathVariable("empId") int id, 
			@PathVariable("empDob") String dob) {
		try {
			Employee e = service.updateEmployeeDob(id, LocalDate.parse(dob));
			return ResponseEntity.status(HttpStatus.OK).body(e);
		} catch (EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": "+"\""+e.getMessage()+"\""+"}");	
		}
	}
	// getting employees based on name
	@GetMapping("{empName}/fetch")
	public ResponseEntity<Object> getEmployeesByName(@PathVariable("empName") String name) {
		try {
			List<Employee> list = service.fetchEmployeesByName(name);
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": "+"\""+e.getMessage()+"\""+"}");	
		}
	}
	// delete employee by id
	@DeleteMapping(path = "{empId}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Object> deleteEmployee(@PathVariable("empId") int id) {
		try {
			service.deleteEmployee(id);
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\": "+"\"Employee with an id "+id+" deleted\""+"}");
		} catch (EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": "+"\""+e.getMessage()+"\""+"}");
		}
	}
}
