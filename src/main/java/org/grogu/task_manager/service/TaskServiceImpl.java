package org.grogu.task_manager.service;

import org.grogu.task_manager.dto.TaskRequest;
import org.grogu.task_manager.entity.Status;
import org.grogu.task_manager.entity.Task;
import org.grogu.task_manager.entity.User;
import org.grogu.task_manager.exeption.AppError;
import org.grogu.task_manager.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) {
        Optional<User> executor = userService.findUserByUsername(taskRequest.getExecutorName());
        if (executor.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Раба с таким именем не " +
                    "существует"), HttpStatus.BAD_REQUEST);
        }

        //Подумать как можно упростить (взять всю инфу из токена или security context) без крестового похода в БД
        String taskRequestAuthor = taskRequest.getAuthor();
        Optional<User> author = userService.findUserByUsername(taskRequestAuthor);
        if (author.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Повелителя с таким именем не " +
                    "существует"), HttpStatus.BAD_REQUEST);
        }

        Task newTask = new Task();
        newTask.setHead(taskRequest.getHead());
        newTask.setDescription(taskRequest.getDescription());
        newTask.setPriority(taskRequest.getPriority());
        newTask.setStatus(Status.WAITING.toString());
        newTask.setAuthor(author.get());
        newTask.setExecutor(executor.get());

        return ResponseEntity.ok().build();
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }
}
