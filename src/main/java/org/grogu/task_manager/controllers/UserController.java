package org.grogu.task_manager.controllers;

import org.grogu.task_manager.entity.User;
import org.grogu.task_manager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class UserController {

    private final UserService userService;

    public UserController(UserService employeeService) {
        this.userService = employeeService;
    }

    @GetMapping()
    public List<User> getEmployees() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getEmployee(@PathVariable Long id) {
        return userService.get(id);
    }



}
