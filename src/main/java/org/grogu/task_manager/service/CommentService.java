package org.grogu.task_manager.service;

import org.grogu.task_manager.dto.CommentRequestDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<?> getAllComments();

    ResponseEntity<?> createNewComment(Long id, CommentRequestDto commentReq);
}
