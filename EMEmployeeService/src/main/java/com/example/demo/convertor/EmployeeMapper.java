package com.example.demo.convertor;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entity.Employee;

@Service
public class EmployeeMapper {
	
	public EmployeeDTO toEmployeeDTO(Employee emp) {
		EmployeeDTO eDto = new EmployeeDTO();
		eDto.setEmployeeId(emp.getId());
		eDto.setName(emp.getName());
		eDto.setEmail(emp.getEmail());
		return eDto;
	}

}
