package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.service.CommentService;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CommentController {
  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping("/posts/{postId}/comments")
  public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId,@RequestBody CommentDto commentDto){
    CommentDto createComment = commentService.creatComment(postId, commentDto);
    return new ResponseEntity<>(createComment, HttpStatus.CREATED);
  }

  @GetMapping("/posts/{postId}/comments")
  public List<CommentDto> getCommentsBypostId(@PathVariable(value="postId") Long postId){
    return commentService.getCommentsByPostId(postId);

  }
  @GetMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                   @PathVariable(value = "id") Long CommentId){
    CommentDto commentDto = commentService.getCommentById(postId, CommentId);
    return new ResponseEntity<>(commentDto, HttpStatus.OK);
  }

  @PutMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> updatedComment(@PathVariable(value = "potId") Long postId,
                                                   @PathVariable(value = "commentId") Long commentId,
                                                   @RequestBody CommentDto commentDto){
    CommentDto updatedComment = commentService.updateComment(postId,commentId,commentDto);
    return new ResponseEntity<>(updatedComment, HttpStatus.OK);

  }

  @DeleteMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                              @PathVariable(value = "commentId") Long commentId){

    commentService.deleteComment(postId,commentId);

    return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
  }
}
