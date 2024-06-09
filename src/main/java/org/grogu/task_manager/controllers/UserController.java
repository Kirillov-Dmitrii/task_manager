package org.grogu.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grogu.task_manager.entity.User;
import org.grogu.task_manager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<User> getUsers() {
        log.info("getting all users");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        log.info("getting user with id: " + id);
        return userService.get(id);
    }

    @GetMapping("/{id}/tasks/author")
    public ResponseEntity<?> getCreatedTasks(@PathVariable Long id) {
        log.info("getting user authors tasks with id: " + id);
        return userService.getCreatedTasks(id);
    }

    @GetMapping("{id}/tasks/executor")
    public ResponseEntity<?> getExecutingTasks(@PathVariable Long id) {
        log.info("getting user executor tasks with id: " + id);
        return userService.getExecutingTasks(id);
    }

}
