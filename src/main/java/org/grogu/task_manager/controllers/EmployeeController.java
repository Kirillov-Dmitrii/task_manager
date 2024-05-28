package org.grogu.task_manager.controllers;

import org.grogu.task_manager.entity.Employee;
import org.grogu.task_manager.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public Employee add(@RequestBody Employee employee) {
        return employeeService.add(employee);
    }


    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id) {
        return employeeService.get(id);
    }



}
