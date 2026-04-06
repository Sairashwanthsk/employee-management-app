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


@Service
public class DepartmentService implements DepartmentServiceInterface {

    // @Autowired
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {
        Department dept = DepartmentMapper.toEntity(dto);
        return DepartmentMapper.toDTO(departmentRepository.save(dept));
    }

    @Override
    public Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {
        Page<Department> departmentPage = departmentRepository.findAll(pageable);
        return departmentPage.map(DepartmentMapper::toDTO);
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Integer id) {
        return DepartmentMapper.toDTO(departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with ID: " + id)));
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Integer id, DepartmentRequestDTO dto) {
        if (departmentRepository.existsById(id)) {
            Department dept = DepartmentMapper.toEntity(dto);
            dept.setId(id);
            return DepartmentMapper.toDTO(departmentRepository.save(dept));
        }
        throw new ResourceNotFoundException("Department not found with ID: " + id);
    }

    @Override
    public void deleteDepartment(Integer id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return;
        }
        throw new ResourceNotFoundException("Department not found with ID: " + id);
    }
}