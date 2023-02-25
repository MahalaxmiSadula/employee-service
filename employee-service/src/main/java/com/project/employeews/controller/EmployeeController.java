package com.project.employeews.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.employeews.exception.ResourceNotFoundException;
import com.project.employeews.model.Employee;
import com.project.employeews.repository.EmployeeRepository;
import com.project.employeews.service.EmployeeService;
import com.project.employeews.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	// get all employees
	@GetMapping("/employees")
	public Collection<Employee> getAllEmployees() {
		return employeeServiceImpl.getListOfEmployees(10);
	}

	// get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeServiceImpl.getEmployee(id);
		return ResponseEntity.ok(employee);
	}

	// add employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeServiceImpl.addEmployee(employee);
	}

	// update employee
	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employeeDetails) {
		Employee employee = employeeServiceImpl.updateEmployee(employeeDetails);
		return ResponseEntity.ok(employee);
	}

	// delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted", employeeServiceImpl.deleteEmployee(id));
		return ResponseEntity.ok(response);
	}

}
