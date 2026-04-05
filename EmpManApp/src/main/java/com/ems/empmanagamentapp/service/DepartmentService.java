package com.ems.empmanagamentapp.service;

import com.ems.empmanagamentapp.model.Department;
import com.ems.empmanagamentapp.repository.DepartmentRepository;
import com.ems.empmanagamentapp.service.interfaces.DepartmentServiceInterface;
import org.springframework.stereotype.Service;
import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DepartmentService implements DepartmentServiceInterface {

    // @Autowired
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));
    }

    @Override
    public Department updateDepartment(Integer id, Department department) {
        if (departmentRepository.existsById(id)) {
            department.setId(id);
            return departmentRepository.save(department);
        }
        throw new RuntimeException("Department not found with ID: " + id);
    }

    @Override
    public void deleteDepartment(Integer id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        }
        throw new RuntimeException("Department not found with ID: " + id);
    }
}