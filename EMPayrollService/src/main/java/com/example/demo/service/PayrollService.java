package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payroll;
import com.example.demo.repo.PayrollRepository;

@Service
public class PayrollService {
	
	
	@Autowired
    private PayrollRepository payrollRepository;

    // Create a new payroll record
    public Payroll savePayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    // Get all payroll records
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    // Get payroll record by ID
    public Optional<Payroll> getPayrollById(Long id) {
        return payrollRepository.findById(id);
    }

    // Update payroll record by ID
    public Payroll updatePayroll(Long id, Payroll updatedPayroll) {
        Optional<Payroll> payrollOpt = payrollRepository.findById(id);
        if (payrollOpt.isPresent()) {
            Payroll payroll = payrollOpt.get();
            payroll.setSalary(updatedPayroll.getSalary());
            payroll.setBonus(updatedPayroll.getBonus());
            payroll.setDeductions(updatedPayroll.getDeductions());
            return payrollRepository.save(payroll);
        }
        return null;
    }

    // Delete payroll record by ID
    public void deletePayroll(Long id) {
        payrollRepository.deleteById(id);
    }

	public Payroll getPayrollByEmployeeId(Long employeeId) {
		// TODO Auto-generated method stub
		return payrollRepository.findByEmployeeId(employeeId);
	}

}
