package com.ems.empmanagamentapp.service.interfaces;

import com.ems.empmanagamentapp.dto.DepartmentRequestDTO;
import com.ems.empmanagamentapp.dto.DepartmentResponseDTO;
import com.ems.empmanagamentapp.model.Department;
import java.util.List;

public interface DepartmentServiceInterface {
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto);

    List<DepartmentResponseDTO> getAllDepartments();

    DepartmentResponseDTO getDepartmentById(Integer id);

    DepartmentResponseDTO updateDepartment(Integer id, DepartmentRequestDTO dto);

    void deleteDepartment(Integer id);
}