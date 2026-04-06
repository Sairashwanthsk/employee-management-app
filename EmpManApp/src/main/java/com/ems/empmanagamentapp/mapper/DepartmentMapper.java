package com.ems.empmanagamentapp.mapper;

import com.ems.empmanagamentapp.dto.DepartmentRequestDTO;
import com.ems.empmanagamentapp.dto.DepartmentResponseDTO;
import com.ems.empmanagamentapp.model.Department;

public class DepartmentMapper {

    public static Department toEntity(DepartmentRequestDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        // Set other properties as needed
        return department;
    }

    public static DepartmentResponseDTO toDTO(Department department) {
        return DepartmentResponseDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}