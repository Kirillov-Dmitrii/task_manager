package org.grogu.task_manager.dto;

import lombok.Data;

@Data
public class TaskRequest {
    private String head;
    private String description;
    private String priority;
    private String author;
    private String executorName;
}
