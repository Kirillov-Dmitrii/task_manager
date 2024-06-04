package org.grogu.task_manager.controllers;

import org.grogu.task_manager.entity.Task;
import org.grogu.task_manager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@RequestBody Task task) {
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        Task taskFromService = taskService.updateTask(task);
        return ResponseEntity.ok(task);
    }

}
