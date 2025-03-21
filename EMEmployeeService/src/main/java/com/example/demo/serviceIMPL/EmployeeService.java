package com.example.demo.serviceIMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.DTO.Department;
import com.example.demo.DTO.EmployeeWithDepartment;
import com.example.demo.DTO.Payroll;
import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	
	@Autowired
    private RestTemplate restTemplate;
	@Autowired
    private EmployeeRepository employeeRepository;
	
	private static final String DEPARTMENT_SERVICE_URL = "http://localhost:8082/departments/name/";
	private static final String PAYROLL_SERVICE_URL = "http://localhost:8083/payrolls";
    // Create a new employee
	@Transactional
    public Employee saveEmployee(Employee employee) {
    	// Fetch department details from Department service
        Department department = restTemplate.getForObject(
            DEPARTMENT_SERVICE_URL + employee.getDepartment(), Department.class);
        
        if (department != null) {
            // Set department information in Employee
            employee.setDepartment(department.getDepartmentName()); 
        } else {
            throw new RuntimeException("Department not found");
        }
        
        Employee savedEmployee = employeeRepository.save(employee);
        //
        // Create a corresponding payroll entry for the employee with values from the request
        Payroll payroll = new Payroll();
        payroll.setEmployeeId(savedEmployee.getId());
        payroll.setSalary(savedEmployee.getSalary()); // Use the salary passed in the request
        payroll.setBonus(savedEmployee.getBonus());   // Use the bonus passed in the request
        payroll.setDeductions(savedEmployee.getDeductions()); // Use deductions passed in the request

        Payroll createdPayroll = restTemplate.postForObject(
            PAYROLL_SERVICE_URL, payroll, Payroll.class);

        if (createdPayroll == null) {
            throw new RuntimeException("Failed to create payroll for the employee");
        }
        //
        return savedEmployee;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public EmployeeWithDepartment getEmployeeById(Long id) {
       Employee emp = employeeRepository.findById(id).orElse(null);
     // Call Department service to get department info
        String departmentServiceUrl = "http://localhost:8082/departments/name/" + emp.getDepartment();
        Department department = restTemplate.getForObject(departmentServiceUrl, Department.class);
        EmployeeWithDepartment empDep = new EmployeeWithDepartment();
        empDep.setEmployee(emp);
        empDep.setDepartment(department);
        return empDep;
    }

    // Update an employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employee.get().setName(updatedEmployee.getName());
            employee.get().setDepartment(updatedEmployee.getDepartment());
            employee.get().setEmail(updatedEmployee.getEmail());
            return employeeRepository.save(employee.get());
        }
        return null;
    }
    

    // Delete an employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
