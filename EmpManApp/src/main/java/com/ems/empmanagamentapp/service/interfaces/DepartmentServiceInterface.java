package com.ems.empmanagamentapp.service.interfaces;

import com.ems.empmanagamentapp.model.Department;
import java.util.List;

public interface DepartmentServiceInterface {
    Department createDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(Integer id);

    Department updateDepartment(Integer id, Department department);

    void deleteDepartment(Integer id);
}