package org.grogu.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grogu.task_manager.dto.CommentRequestDto;
import org.grogu.task_manager.dto.StatusRequestDto;
import org.grogu.task_manager.dto.TaskRequest;
import org.grogu.task_manager.service.CommentService;
import org.grogu.task_manager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Slf4j
public class TaskController {
    private final TaskService taskService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        log.info("getAllTasks");
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) {
        log.info("creating new task: " + taskRequest);
        return taskService.createTask(taskRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        log.info("updating task: " + id + " " + taskRequest);
        return taskService.updateTask(id, taskRequest);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long id, @RequestBody StatusRequestDto statusReq) {
        log.info("updating task status");
        return taskService.updateTaskStatus(id, statusReq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTask(@PathVariable Long id) {
        log.info("removing task: " + id);
        return taskService.removeTask(id);
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<?> createNewComment(@PathVariable Long id, @RequestBody CommentRequestDto commentReq) {
        log.info("creating new comment: " + commentReq);
        return commentService.createNewComment(id, commentReq);
    }



}
