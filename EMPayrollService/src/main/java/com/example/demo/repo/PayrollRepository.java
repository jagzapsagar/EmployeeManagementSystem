package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

	Payroll findByEmployeeId(Long employeeId);

}
