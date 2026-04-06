package com.ems.empmanagamentapp.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class EmployeeResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String departmentName;
}