package com.ems.empmanagamentapp.repository;

import com.ems.empmanagamentapp.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Page<Employee> findByDepartmentId(Integer departmentId, Pageable pageable);
    
}