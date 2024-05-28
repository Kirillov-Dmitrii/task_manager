package org.grogu.task_manager.service;

import org.grogu.task_manager.entity.Employee;
import org.grogu.task_manager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void remove(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> get(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }
}
