package com.ems.empmanagamentapp.service.interfaces;

import com.ems.empmanagamentapp.dto.EmployeeRequestDTO;
import com.ems.empmanagamentapp.dto.EmployeeResponseDTO;
import com.ems.empmanagamentapp.model.Employee;
import java.util.List;

public interface EmployeeServiceInterface {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(Integer id);

    EmployeeResponseDTO updateEmployee(Integer id, EmployeeRequestDTO dto);

    void deleteEmployee(Integer id);

    List<EmployeeResponseDTO> getEmployeesByDepartmentId(Integer departmentId);
}