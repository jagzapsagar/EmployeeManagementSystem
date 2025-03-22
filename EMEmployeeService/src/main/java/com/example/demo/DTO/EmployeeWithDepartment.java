package com.example.demo.DTO;

import com.example.demo.entity.Employee;

public class EmployeeWithDepartment {
	
	private Employee employee;
    private Department department;
    private Payroll payroll;
    
    public EmployeeWithDepartment() {
       
    }

	public EmployeeWithDepartment(Employee employee, Department department, Payroll payroll) {
		super();
		this.employee = employee;
		this.department = department;
		this.payroll = payroll;
	}

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

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

	@Override
	public String toString() {
		return "EmployeeWithDepartment [employee=" + employee + ", department=" + department + ", payroll=" + payroll
				+ "]";
	}

    
    
}
