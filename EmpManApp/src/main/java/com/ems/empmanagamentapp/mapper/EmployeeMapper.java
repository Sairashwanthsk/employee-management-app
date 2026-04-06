package com.ems.empmanagamentapp.mapper;

import com.ems.empmanagamentapp.dto.EmployeeRequestDTO;
import com.ems.empmanagamentapp.dto.EmployeeResponseDTO;
import com.ems.empmanagamentapp.model.Department;
import com.ems.empmanagamentapp.model.Employee;


public class EmployeeMapper {
    
    public static Employee toEntity(EmployeeRequestDTO dto, Department department) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(department);
        // Set other properties as needed
        return employee;
    }

    public static EmployeeResponseDTO toDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .departmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null)
                .build();
    }
}