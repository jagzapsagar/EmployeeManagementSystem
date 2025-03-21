package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Payroll;
import com.example.demo.service.PayrollService;

@RestController
@RequestMapping("/payrolls")
public class PayrollController {
	
	@Autowired
    private PayrollService payrollService;

    // Create a new payroll record
    @PostMapping
    public Payroll createPayroll(@RequestBody Payroll payroll) {
        return payrollService.savePayroll(payroll);
    }

    // Get all payroll records
    @GetMapping
    public List<Payroll> getAllPayrolls() {
        return payrollService.getAllPayrolls();
    }

    // Get payroll record by ID
    @GetMapping("/{id}")
    public Optional<Payroll> getPayrollById(@PathVariable Long id) {
        return payrollService.getPayrollById(id);
    }
    
    @GetMapping("/emp/{employeeId}")
    public Payroll getPayrollByEmployeeId(@PathVariable Long employeeId) {
        return payrollService.getPayrollByEmployeeId(employeeId);
    }

    // Update payroll record by ID
    @PutMapping("/{id}")
    public Payroll updatePayroll(@PathVariable Long id, @RequestBody Payroll updatedPayroll) {
        return payrollService.updatePayroll(id, updatedPayroll);
    }

    // Delete payroll record by ID
    @DeleteMapping("/{id}")
    public void deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
    }

}
