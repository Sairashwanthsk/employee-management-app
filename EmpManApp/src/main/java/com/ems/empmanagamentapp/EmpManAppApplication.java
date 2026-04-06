package com.ems.empmanagamentapp;

import org.springframework.boot.SpringApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
	info = @Info(
		title = "Employee Management API",
		version = "3.1.0",
		description = "Spring Boot REST API for managing employees in an organization"
	)
)
@SpringBootApplication
public class EmpManAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpManAppApplication.class, args);
	}

}
