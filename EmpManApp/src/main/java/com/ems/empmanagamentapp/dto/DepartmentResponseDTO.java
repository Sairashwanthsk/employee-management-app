package com.ems.empmanagamentapp.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class DepartmentResponseDTO {
    private Integer id;
    private String name;
}