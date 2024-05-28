package org.grogu.task_manager.service;

import org.grogu.task_manager.entity.Task;

public interface TaskService {
    Task addTask(Task task);

    Task updateTask(Task task);
}
