package org.grogu.task_manager.service;

import org.grogu.task_manager.dto.StatusRequestDto;
import org.grogu.task_manager.dto.TaskRequest;
import org.grogu.task_manager.entity.Task;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    ResponseEntity<?> createTask(TaskRequest taskRequest);

    ResponseEntity<?> updateTask(Long id, TaskRequest taskRequest);

    ResponseEntity<?> removeTask(Long id);

    ResponseEntity<?> updateTaskStatus(Long id, StatusRequestDto statusReq);

    ResponseEntity<?> getAllTasks();
}
