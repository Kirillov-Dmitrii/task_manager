package org.grogu.task_manager.service;

import org.apache.catalina.LifecycleState;
import org.grogu.task_manager.dto.StatusRequestDto;
import org.grogu.task_manager.dto.TaskRequest;
import org.grogu.task_manager.entity.Status;
import org.grogu.task_manager.entity.Task;
import org.grogu.task_manager.entity.User;
import org.grogu.task_manager.exeption.AppError;
import org.grogu.task_manager.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.webjars.NotFoundException;

import java.util.List;
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


    //Подумать, как не извлекать весь объект, а обновить только то поле, которое было отредактировано
    @Override
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        Optional<Task> taskFromDB = taskRepository.findById(id);
        if (taskFromDB.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Задачи с таким названием не " +
                    "существует"), HttpStatus.BAD_REQUEST);
        }

        Optional<User> executor = userService.findUserByUsername(taskRequest.getExecutorName());
        if (executor.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Раба с таким именем не " +
                    "существует"), HttpStatus.BAD_REQUEST);
        }

        taskFromDB.get().setHead(taskRequest.getHead());
        taskFromDB.get().setDescription(taskRequest.getDescription());
        taskFromDB.get().setPriority(taskRequest.getPriority());
        taskFromDB.get().setExecutor(executor.get());

        taskRepository.save(taskFromDB.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> removeTask(Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updateTaskStatus(Long id, @RequestBody StatusRequestDto statusReq) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Такой задачи не существет"));
        task.setStatus(statusReq.getStatus());
        taskRepository.save(task);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> getAllTasks() {
        List<Task> list = taskRepository.findAll();
        return ResponseEntity.ok(list);
    }
}
