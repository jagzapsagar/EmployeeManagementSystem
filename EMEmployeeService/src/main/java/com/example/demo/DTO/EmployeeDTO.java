package com.example.demo.DTO;

public class EmployeeDTO {
	private Long employeeId;   // Add employeeId for scenarios where it's needed (like update)
    private String name;
    private String email;
    
    //Department Fields
    private Long departmentId;
    private String departmentName;
    private String departmentCode;

    // Payroll-related fields for request
    private Double salary;
    private Double bonus;
    private Double deductions;
	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDTO(Long employeeId, String name, String email, Long departmentId, String departmentName,
			String departmentCode, Double salary, Double bonus, Double deductions) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
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
		return "EmployeeDTO [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", departmentId="
				+ departmentId + ", departmentName=" + departmentName + ", departmentCode=" + departmentCode
				+ ", salary=" + salary + ", bonus=" + bonus + ", deductions=" + deductions + "]";
	}
	
	
    
    

}
