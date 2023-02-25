package com.project.employeews.service;

import java.util.Collection;

import com.project.employeews.model.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);
	 
	 Collection<Employee> getListOfEmployees(int limit);
	 
	 Employee getEmployee(Long id);
	 
	 Employee updateEmployee(Employee emp);
	 
	 Boolean deleteEmployee(Long id);
}
