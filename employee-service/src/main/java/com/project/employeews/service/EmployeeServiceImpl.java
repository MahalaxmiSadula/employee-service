package com.project.employeews.service;

import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.employeews.exception.ResourceNotFoundException;
import com.project.employeews.model.Employee;
import com.project.employeews.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		Employee savedEmployee = null;
		if (employee != null) {
			savedEmployee = employeeRepository.save(employee);
		}
		return savedEmployee;
	}

	@Override
	public Collection<Employee> getListOfEmployees(int limit) {
		return employeeRepository.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Employee getEmployee(Long id) {

		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee doen't exist with id: " + id));
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}

	@Override
	public Boolean deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return true;
	}

}
