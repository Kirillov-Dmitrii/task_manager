package org.grogu.task_manager.service;

import org.grogu.task_manager.dto.TaskRequest;
import org.grogu.task_manager.entity.Task;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    ResponseEntity<?> createTask(TaskRequest taskRequest);

    Task updateTask(Task task);
}
