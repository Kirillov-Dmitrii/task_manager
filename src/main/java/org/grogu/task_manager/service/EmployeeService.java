package org.grogu.task_manager.service;

import org.grogu.task_manager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void remove(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
