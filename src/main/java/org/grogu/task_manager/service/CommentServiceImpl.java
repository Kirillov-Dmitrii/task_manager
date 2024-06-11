package org.grogu.task_manager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grogu.task_manager.dto.CommentRequestDto;
import org.grogu.task_manager.entity.Comment;
import org.grogu.task_manager.entity.Task;
import org.grogu.task_manager.repository.CommentRepository;
import org.grogu.task_manager.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Override
    public ResponseEntity<?> getAllComments() {
        List<Comment> list = commentRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<?> createNewComment(Long id, CommentRequestDto commentReq) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Такой задачи нет"));
        Comment comment = new Comment();
        comment.setTask(task);
        comment.setText(commentReq.getText());

        commentRepository.save(comment);
        return ResponseEntity.ok(comment);
    }
}
