package com.example.demo.DTO;

import com.example.demo.entity.Employee;

public class EmployeeWithDepartment {
	
	private Employee employee;
    private Department department;
    
    public EmployeeWithDepartment() {
       
    }

    // Constructors
    public EmployeeWithDepartment(Employee employee, Department department) {
        this.employee = employee;
        this.department = department;
    }

    // Getters and Setters
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

	@Override
	public String toString() {
		return "EmployeeWithDepartment [employee=" + employee + ", department=" + department + "]";
	}
    
}
