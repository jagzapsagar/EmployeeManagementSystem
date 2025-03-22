package com.example.demo.DTO;

public class EmployeeDTO {
	private Long employeeId;   // Add employeeId for scenarios where it's needed (like update)
    private String name;
    private String department;
    private String email;

    // Payroll-related fields for request
    private Double salary;
    private Double bonus;
    private Double deductions;
	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDTO(Long employeeId, String name, String department, String email, Double salary, Double bonus,
			Double deductions) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.email = email;
		this.salary = salary;
		this.bonus = bonus;
		this.deductions = deductions;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Double getDeductions() {
		return deductions;
	}
	public void setDeductions(Double deductions) {
		this.deductions = deductions;
	}
	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", name=" + name + ", department=" + department + ", email="
				+ email + ", salary=" + salary + ", bonus=" + bonus + ", deductions=" + deductions + "]";
	}
    
    

}
