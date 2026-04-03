package com.ems.empmanagamentapp.repository;
import com.ems.empmanagamentapp.model.Employee;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class EmployeeRepository {
    // This is a simple in-memory repository for demonstration purposes.
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        // Adding some dummy data
        employees.add(new Employee(1, "John Doe", "Engineering"));
        employees.add(new Employee(2, "Jane Smith", "Marketing"));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(int id) {
        return employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Employee employee) {
        employees.add(employee);
    }

    public void update(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == employee.getId()) {
                employees.set(i, employee);
                return;
            }
        }
    }

    public void deleteById(int id) {
        employees.removeIf(emp -> emp.getId() == id);
    }
}