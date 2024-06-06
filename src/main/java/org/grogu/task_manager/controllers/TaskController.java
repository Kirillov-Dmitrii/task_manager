package org.grogu.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.grogu.task_manager.dto.TaskRequest;
import org.grogu.task_manager.entity.Task;
import org.grogu.task_manager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        Task taskFromService = taskService.updateTask(task);
        return ResponseEntity.ok(task);
    }

}
