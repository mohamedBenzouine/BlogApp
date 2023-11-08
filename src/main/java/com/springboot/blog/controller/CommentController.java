package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.service.CommentService;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommentController {

  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  public ResponseEntity<CommentDto> createComment(@PathParam(value = "postId") Long postId,@RequestBody CommentDto commentDto){
    CommentDto createComment = commentService.creatComment(postId, commentDto);
    return new ResponseEntity<>(createComment, HttpStatus.CREATED);
  }




}
