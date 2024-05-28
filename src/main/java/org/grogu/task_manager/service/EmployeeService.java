package org.grogu.task_manager.service;

import org.grogu.task_manager.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();
    Optional<Employee> get(Long id);

    Employee add(Employee employee);
}
