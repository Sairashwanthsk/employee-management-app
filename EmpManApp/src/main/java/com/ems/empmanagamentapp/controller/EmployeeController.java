package com.ems.empmanagamentapp.controller;
import com.ems.empmanagamentapp.service.EmployeeService;
import org.springframework.web.bind.annotation.RestController;

import com.ems.empmanagamentapp.dto.EmployeeRequestDTO;
import com.ems.empmanagamentapp.dto.EmployeeResponseDTO;
import com.ems.empmanagamentapp.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    // public List<EmployeeResponseDTO> getEmployees(@RequestParam(required = false) Integer departmentId) {
    public Page<EmployeeResponseDTO> getEmployees(Pageable pageable, @RequestParam(required = false) Integer departmentId) {
        if (departmentId != null) {
            return employeeService.getEmployeesByDepartmentId(departmentId, pageable);
        }
        return employeeService.getAllEmployees(pageable);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeResponseDTO createEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
        return employeeService.createEmployee(dto);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@Valid @PathVariable Integer id, @RequestBody EmployeeRequestDTO dto) {
        return employeeService.updateEmployee(id, dto);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee with ID " + id + " has been deleted.");
        return response;
    }
}