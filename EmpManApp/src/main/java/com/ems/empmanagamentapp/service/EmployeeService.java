package com.ems.empmanagamentapp.service;

import com.ems.empmanagamentapp.model.Employee;
import com.ems.empmanagamentapp.repository.EmployeeRepository;
import com.ems.empmanagamentapp.service.interfaces.EmployeeServiceInterface;
import org.springframework.stereotype.Service;
import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    // @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        if (employeeRepository.existsById(id)) {
            employee.setId(id);
            return employeeRepository.save(employee);
        }
        throw new RuntimeException("Employee not found with ID: " + id);
    }

    @Override
    public void deleteEmployee(Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return;
        }
        throw new RuntimeException("Employee not found with ID: " + id);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Integer departmentId) {
        if (!employeeRepository.existsById(departmentId)) {
            throw new RuntimeException("Department not found with ID: " + departmentId);
        }
        return employeeRepository.findByDepartmentId(departmentId);
    }

}