package com.example.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.EmployeeNotFoundException;
import com.example.model.beans.Employee;
import com.example.model.dao.EmployeeDao;
@Service // registers the class as a bean so that spring container can create its instance & supply to another instance
public class EmployeeServiceImpl implements EmployeeService {

	//injects the Dao instance that is implemented by spring boot
	@Autowired
	private EmployeeDao dao;
	
	@Transactional
	@Override
	public Employee storeEmployee(Employee employee) {
		Employee e = dao.save(employee);
		return e;
	}

	@Override
	public Employee fetchEmployeeById(int id) throws EmployeeNotFoundException {
		Employee e = dao.findById(id).orElse(null);
		if(e == null) {
			throw new EmployeeNotFoundException("Sorry employee id '"+id+"' not found");
		}
		return e;
	}

	@Override
	public List<Employee> fetchEmployees() {
		List<Employee> list = dao.findAll();
		return list;
	}

	@Transactional
	@Override
	public void deleteEmployee(int id) throws EmployeeNotFoundException {
		Employee e = fetchEmployeeById(id);
		dao.delete(e);
	}

	@Transactional
	@Override
	public Employee updateEmployeeDob(int id, LocalDate dob) throws EmployeeNotFoundException {
		Employee e = fetchEmployeeById(id);
		e.setDob(dob);
		dao.save(e); // it updates the entity if the id already exists
		return e;
	}

	@Override
	public List<Employee> fetchEmployeesByName(String name) throws EmployeeNotFoundException {
		List<Employee> list = dao.findEmployeesByName(name);
		if(list.size() == 0) {
			throw new EmployeeNotFoundException("Sorry no employees with the name: '"+name+"' found");
		}
		return list;
	}

}
