package com.ems.empmanagamentapp.service.interfaces;

import com.ems.empmanagamentapp.model.Employee;
import java.util.List;

public interface EmployeeServiceInterface {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    Employee updateEmployee(Integer id, Employee employee);

    void deleteEmployee(Integer id);

    List<Employee> getEmployeesByDepartmentId(Integer departmentId);
}