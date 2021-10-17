package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.exception.ResourceNotFoundedException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// Get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// Create new employee
	// Sin el request body los datos no se guardan
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// Get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundedException("Employee not exist with id:" + id));

		return ResponseEntity.ok(employee);

	}

	// update Employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee employeeSearched = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundedException("Employee not exist with id:" + id));
		
		employeeSearched.setFirstName(employee.getFirstName());
		employeeSearched.setLastName(employee.getLastName());
		employeeSearched.setEmailId(employee.getEmailId());
		
		employeeRepository.save(employeeSearched);
		
		Employee employeeUpdated = employeeRepository.save(employeeSearched);
		
		return ResponseEntity.ok(employeeUpdated);
	}
}
