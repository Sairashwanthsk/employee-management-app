package com.ems.empmanagamentapp.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class DepartmentRequestDTO {
    @NotNull(message = "Department name cannot be null")
    @NotEmpty(message = "Department name cannot be empty")
    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    private String name;
}