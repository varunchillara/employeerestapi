package com.java.learning.employee.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.learning.employee.myapp.exception.ResourceNotFoundException;
import com.java.learning.employee.myapp.model.Employee;
import com.java.learning.employee.myapp.repository.EmployeeRepository;

@RestController
@RequestMapping("/myemployeeapp")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRep;
	
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(value="employeeId")Integer employeeId) throws ResourceNotFoundException {
		
		Employee employee = employeeRep.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(
				"employee not found. not valid id " + employeeId ));
		
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping("/employees")
	public Employee insertEmployee(@Valid @RequestBody Employee employee) {
		return employeeRep.save(employee);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRep.findAll();
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public Map<String, Boolean> deleteEmployees(@PathVariable(value="employeeId")Integer employeeId) throws ResourceNotFoundException  {
		Employee employee = employeeRep.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(
				"employee not found. not valid id " + employeeId ));
		
		employeeRep.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", true);
		
		return response;
	}
	
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="employeeId")Integer employeeId
			, @Valid @RequestBody Employee employee) throws ResourceNotFoundException {
		Employee employeeUpdated = employeeRep.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(
				"employee not found. not valid id " + employeeId ));
		
		employeeUpdated.setEmployeeName(employee.getEmployeeName());
		employeeUpdated.setEmployeePosition(employee.getEmployeePosition());
		
		return ResponseEntity.ok().body(employeeRep.save(employeeUpdated));
		
		
	}
}
