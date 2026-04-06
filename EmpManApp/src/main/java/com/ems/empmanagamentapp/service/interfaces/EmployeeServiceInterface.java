package com.ems.empmanagamentapp.service.interfaces;

import com.ems.empmanagamentapp.dto.EmployeeRequestDTO;
import com.ems.empmanagamentapp.dto.EmployeeResponseDTO;
import com.ems.empmanagamentapp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeServiceInterface {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable);

    EmployeeResponseDTO getEmployeeById(Integer id);

    EmployeeResponseDTO updateEmployee(Integer id, EmployeeRequestDTO dto);

    void deleteEmployee(Integer id);

    Page<EmployeeResponseDTO> getEmployeesByDepartmentId(Integer departmentId, Pageable pageable);
}