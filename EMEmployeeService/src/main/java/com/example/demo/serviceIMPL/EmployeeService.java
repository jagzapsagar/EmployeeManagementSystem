package com.example.demo.serviceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

import com.example.demo.DTO.Department;
import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.DTO.Payroll;
import com.example.demo.convertor.EmployeeMapper;
import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private EmployeeMapper employeeMapper;

	private static final String DEPARTMENT_SERVICE_URL = "http://localhost:8088/departments/name/";
	private static final String PAYROLL_SERVICE_URL = "http://localhost:8088/payrolls";

	@Transactional
	public Employee saveEmployee(Employee employee) {

		String token = request.getHeader("Authorization");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token); // reuse it
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Fetch department details from Department service
		ResponseEntity<Department> department = restTemplate.exchange(DEPARTMENT_SERVICE_URL + employee.getDepartment(),
				HttpMethod.GET, entity, Department.class);

		if (department != null) {
			// Set department information in Employee
			employee.setDepartment(department.getBody().getDepartmentName());
		} else {
			throw new RuntimeException("Department not found");
		}

		Employee savedEmployee = employeeRepository.save(employee);
		//
		// Create a corresponding payroll entry for the employee with values from the
		// request
		Payroll payroll = new Payroll();
		payroll.setEmployeeId(savedEmployee.getId());
		payroll.setSalary(savedEmployee.getSalary()); // Use the salary passed in the request
		payroll.setBonus(savedEmployee.getBonus()); // Use the bonus passed in the request
		payroll.setDeductions(savedEmployee.getDeductions()); // Use deductions passed in the request

		Payroll createdPayroll = restTemplate.postForObject(PAYROLL_SERVICE_URL, payroll, Payroll.class);

		if (createdPayroll == null) {
			throw new RuntimeException("Failed to create payroll for the employee");
		}
		//
		return savedEmployee;
	}

	// Get all employees
	public List<EmployeeDTO> getAllEmployees() {
		String token = request.getHeader("Authorization");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token); // reuse it
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		List<Employee> findAll = employeeRepository.findAll();
		List<EmployeeDTO> employeeDTOList = new ArrayList<>();
		
		for (Employee emp : findAll) {
			EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(emp);
			try {
	            // Department service call
	            ResponseEntity<Department> dResponse = restTemplate.exchange(
	                DEPARTMENT_SERVICE_URL + emp.getDepartment(),
	                HttpMethod.GET, entity, Department.class);

	            Department department = dResponse.getBody();
	            employeeDTO.setDepartmentName(department.getDepartmentName());
	            employeeDTO.setDepartmentCode(department.getDepartmentCode());
	            employeeDTO.setDepartmentId(department.getId());

	        }catch (Exception e) {
	            System.out.println("Failed to fetch department for employee ID " + emp.getId() + ": " + e.getMessage());
	        }
			try {
	            // Payroll service call
	            ResponseEntity<Payroll> pResponse = restTemplate.exchange(
	                PAYROLL_SERVICE_URL + "/emp/" + emp.getId(),
	                HttpMethod.GET, entity, Payroll.class);

	            Payroll payroll = pResponse.getBody();
	            employeeDTO.setSalary(payroll.getSalary());
	            employeeDTO.setBonus(payroll.getBonus());
	            employeeDTO.setDeductions(payroll.getDeductions());

	        } catch (Exception e) {
	            System.out.println("Failed to fetch payroll for employee ID " + emp.getId() + ": " + e.getMessage());
	        }
			employeeDTOList.add(employeeDTO);
		}
		
		return employeeDTOList;
		
		
	}

	// Get employee by ID
	public EmployeeDTO getEmployeeById(Long id) {

		String token = request.getHeader("Authorization");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token); // reuse it
		HttpEntity<String> entity = new HttpEntity<>(headers);

		System.out.println("-----token--- : " + entity);
		Employee emp = employeeRepository.findById(id).orElse(null);
		EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(emp);
		ResponseEntity<Department> Dresponse = restTemplate.exchange(DEPARTMENT_SERVICE_URL + emp.getDepartment(),
				HttpMethod.GET, entity, Department.class);
		ResponseEntity<Payroll> response = restTemplate.exchange(PAYROLL_SERVICE_URL + "/emp/" + emp.getId(),
				HttpMethod.GET, entity, Payroll.class);
		// System.out.println("--------Department Response: ---"+Dresponse);
		// DepartMent
		employeeDTO.setDepartmentName(Dresponse.getBody().getDepartmentName());
		employeeDTO.setDepartmentCode(Dresponse.getBody().getDepartmentCode());
		employeeDTO.setDepartmentId(Dresponse.getBody().getId());
		// Payroll
		employeeDTO.setSalary(response.getBody().getSalary());
		employeeDTO.setBonus(response.getBody().getBonus());
		employeeDTO.setDeductions(response.getBody().getDeductions());

		return employeeDTO;
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
