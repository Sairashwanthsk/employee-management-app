package com.ems.empmanagamentapp.service;

import com.ems.empmanagamentapp.dto.EmployeeRequestDTO;
import com.ems.empmanagamentapp.dto.EmployeeResponseDTO;
import com.ems.empmanagamentapp.mapper.EmployeeMapper;
import com.ems.empmanagamentapp.model.Department;
import com.ems.empmanagamentapp.model.Employee;
import com.ems.empmanagamentapp.repository.AppLogRepository;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class EmployeeService implements EmployeeServiceInterface {

    // @Autowired
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private final LogService logService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, AppLogRepository appLogRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.logService = new LogService(appLogRepository);
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        try {
            Department dept = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> {
                        log.error("Department not found with ID: " + dto.getDepartmentId());
                        // logService.log("ERROR", "Department not found with ID: " + dto.getDepartmentId());
                        return new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId());
                    });

            Employee employee = EmployeeMapper.toEntity(dto, dept);

            Employee saved = employeeRepository.save(employee);

            return EmployeeMapper.toDTO(saved);
        } catch (ResourceNotFoundException ex) {
            log.error("Failed to create employee: " + ex.getMessage());
            // logService.log("ERROR", "Failed to create employee: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error while creating employee: " + ex.getMessage());
            // logService.log("ERROR", "Unexpected error while creating employee: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while creating the employee.");
        }
    }

    @Override
    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
        try {
            Page<Employee> employeePage = employeeRepository.findAll(pageable);
            return employeePage.map(EmployeeMapper::toDTO);
        } catch (Exception ex) {
            log.error("Unexpected error while fetching employees: " + ex.getMessage());
            // logService.log("ERROR", "Unexpected error while fetching employees: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while fetching employees.");
        }
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Integer id) {
        try {
            return EmployeeMapper.toDTO(employeeRepository.findById(id).orElseThrow(() -> {
                log.error("Employee not found with ID: " + id);
                // logService.log("ERROR", "Employee not found with ID: " + id);
                return new ResourceNotFoundException("Employee not found with ID: " + id);
            }));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error while fetching employee by ID: " + ex.getMessage());
            // logService.log("ERROR", "Unexpected error while fetching employee by ID: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while fetching the employee.");
        }
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Integer id, EmployeeRequestDTO dto) {
        try {
            if (employeeRepository.existsById(id)) {
                Department dept = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));
                Employee employee = EmployeeMapper.toEntity(dto, dept);
                employee.setId(id);
                return EmployeeMapper.toDTO(employeeRepository.save(employee));
            }
            throw new ResourceNotFoundException("Employee not found with ID: " + id);
        } catch (ResourceNotFoundException ex) {
            log.error("Failed to update employee: " + ex.getMessage());
            // logService.log("ERROR", "Failed to update employee: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error while updating employee: " + ex.getMessage());
            // logService.log("ERROR", "Unexpected error while updating employee: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while updating the employee.");
        }
    }

    @Override
    public void deleteEmployee(Integer id) {
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return;
            }
            throw new ResourceNotFoundException("Employee not found with ID: " + id);
        } catch (ResourceNotFoundException ex) {
            log.error("Failed to delete employee: " + ex.getMessage());
            // logService.log("ERROR", "Failed to delete employee: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error while deleting employee: " + ex.getMessage());
            // logService.log("ERROR", "Unexpected error while deleting employee: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while deleting the employee.");
        }
    }

    @Override
    public Page<EmployeeResponseDTO> getEmployeesByDepartmentId(Integer departmentId, Pageable pageable) {
        try {
            if (!departmentRepository.existsById(departmentId)) {
                throw new ResourceNotFoundException("Department not found with ID: " + departmentId);
            }
            return employeeRepository.findByDepartmentId(departmentId, pageable).map(EmployeeMapper::toDTO);
        } catch (Exception ex) {
            log.error("Unexpected error while fetching employees by department ID: " + ex.getMessage());
            // logService.log("ERROR", "Unexpected error while fetching employees by department ID: " + ex.getMessage());
            throw new RuntimeException("An unexpected error occurred while fetching employees by department ID.");
        }
    }

}