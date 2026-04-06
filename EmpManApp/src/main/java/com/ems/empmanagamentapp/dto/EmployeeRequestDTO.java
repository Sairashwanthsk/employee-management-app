package com.ems.empmanagamentapp.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
public class EmployeeRequestDTO {

    @NotBlank(message = "Employee name cannot be blank")
    private String name;

    @Email(message = "Invalid email format")
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    private Integer departmentId;
}