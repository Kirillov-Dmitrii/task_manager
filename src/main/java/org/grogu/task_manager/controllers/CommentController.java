package org.grogu.task_manager.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grogu.task_manager.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private  final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getAllComments() {
        return commentService.getAllComments();
    }

}
