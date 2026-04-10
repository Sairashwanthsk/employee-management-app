package com.ems.empmanagamentapp.service;

import com.ems.empmanagamentapp.dto.DepartmentRequestDTO;
import com.ems.empmanagamentapp.dto.DepartmentResponseDTO;
import com.ems.empmanagamentapp.mapper.DepartmentMapper;
import com.ems.empmanagamentapp.model.Department;
import com.ems.empmanagamentapp.repository.DepartmentRepository;
import com.ems.empmanagamentapp.service.interfaces.DepartmentServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import com.ems.empmanagamentapp.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class DepartmentService implements DepartmentServiceInterface {

    // @Autowired
    private final DepartmentRepository departmentRepository;
    private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {
        try {
            Department dept = DepartmentMapper.toEntity(dto);
            return DepartmentMapper.toDTO(departmentRepository.save(dept));
        } catch (Exception ex) {
            log.error("Unexpected error while creating department: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while creating the department.");
        }
    }

    @Override
    public Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {
        try {
            Page<Department> departmentPage = departmentRepository.findAll(pageable);
            return departmentPage.map(DepartmentMapper::toDTO);
        } catch (Exception ex) {
            log.error("Unexpected error while fetching departments: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while fetching departments.");
        }
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Integer id) {
        try {
            return DepartmentMapper.toDTO(departmentRepository.findById(id).orElseThrow(() -> {
                log.error("Department not found with ID: " + id);
                return new ResourceNotFoundException("Department not found with ID: " + id);
            }));
        } catch (ResourceNotFoundException ex) {
            log.error("Unexpected error: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error while fetching department by ID: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while fetching the department.");
        }
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Integer id, DepartmentRequestDTO dto) {
        try {
            if (departmentRepository.existsById(id)) {
                Department dept = DepartmentMapper.toEntity(dto);
                dept.setId(id);
                return DepartmentMapper.toDTO(departmentRepository.save(dept));
            }
            throw new ResourceNotFoundException("Department not found with ID: " + id);
        } catch (ResourceNotFoundException ex) {
            log.error("Unexpected error: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error while updating department: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while updating the department.");
        }
    }

    @Override
    public void deleteDepartment(Integer id) {
        try {
            if (departmentRepository.existsById(id)) {
                departmentRepository.deleteById(id);
                return;
            }
            throw new ResourceNotFoundException("Department not found with ID: " + id);
        } catch (ResourceNotFoundException ex) {
            log.error("Unexpected error: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error while deleting department: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while deleting the department.");
        }
    }
}