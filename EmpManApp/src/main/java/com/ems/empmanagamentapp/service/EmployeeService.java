package com.ems.empmanagamentapp.service;

import com.ems.empmanagamentapp.dto.EmployeeRequestDTO;
import com.ems.empmanagamentapp.dto.EmployeeResponseDTO;
import com.ems.empmanagamentapp.mapper.EmployeeMapper;
import com.ems.empmanagamentapp.model.Department;
import com.ems.empmanagamentapp.model.Employee;
import com.ems.empmanagamentapp.repository.DepartmentRepository;
import com.ems.empmanagamentapp.repository.EmployeeRepository;
import com.ems.empmanagamentapp.exception.ResourceNotFoundException;
import com.ems.empmanagamentapp.service.interfaces.EmployeeServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    // @Autowired
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

        Department dept = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));

        Employee employee = EmployeeMapper.toEntity(dto, dept);

        return EmployeeMapper.toDTO(employeeRepository.save(employee));
    }

    @Override
    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(EmployeeMapper::toDTO);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Integer id) {
        return EmployeeMapper.toDTO(employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id)));
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Integer id, EmployeeRequestDTO dto) {
        if (employeeRepository.existsById(id)) {
            Department dept = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));
            Employee employee = EmployeeMapper.toEntity(dto, dept);
            employee.setId(id);
            return EmployeeMapper.toDTO(employeeRepository.save(employee));
        }
        throw new ResourceNotFoundException("Employee not found with ID: " + id);
    }

    @Override
    public void deleteEmployee(Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return;
        }
        throw new ResourceNotFoundException("Employee not found with ID: " + id);
    }

    @Override
    public Page<EmployeeResponseDTO> getEmployeesByDepartmentId(Integer departmentId, Pageable pageable) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found with ID: " + departmentId);
        }
        return employeeRepository.findByDepartmentId(departmentId, pageable).map(EmployeeMapper::toDTO);
    }

}