package com.ems.empmanagamentapp.repository;

import com.ems.empmanagamentapp.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByDepartmentId(Integer departmentId);
    
}