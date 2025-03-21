package com.example.demo.DTO;

public class Payroll {
	
	private Long employeeId;
    private Double salary;
    private Double bonus;
    private Double deductions;
	public Payroll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payroll(Long employeeId, Double salary, Double bonus, Double deductions) {
		super();
		this.employeeId = employeeId;
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
		return "Payroll [employeeId=" + employeeId + ", salary=" + salary + ", bonus=" + bonus + ", deductions="
				+ deductions + "]";
	}
    
    

}
