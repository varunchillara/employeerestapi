package com.java.learning.employee.myapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	private Integer employeeId;
	private String employeeName;
	private String employeePosition;
	
	public Employee() {}
	
	public Employee(String employeeName, String employeePosition) {
		super();
		this.employeeName = employeeName;
		this.employeePosition = employeePosition;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="employeeid")
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	@Column(name="employeename")
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@Column(name="employeeposition")
	public String getEmployeePosition() {
		return employeePosition;
	}
	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}
	
}
