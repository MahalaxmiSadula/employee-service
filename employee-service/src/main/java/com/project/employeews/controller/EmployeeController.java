package com.project.employeews.controller;

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

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("employee doen't exist with id: " + id));
		return ResponseEntity.ok(employee);
	}

	// add employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> addEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("employee doen't exist with id: " + id));

		employee.setEmail(employeeDetails.getEmail());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());

		employeeRepository.save(employee);
		return ResponseEntity.ok(employee);
	}
	// delete employee
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id) {

//			Employee employee = employeeRepository.findById(id)
//					.orElseThrow(() -> new ResourceNotFoundException("employee doen't exist with id: " + id));
			Map<String,Boolean> response=new HashMap<String, Boolean>();
			
			employeeRepository.deleteById(id);
			response.put("Deleted", true);
			return ResponseEntity.ok(response);
		}

}
