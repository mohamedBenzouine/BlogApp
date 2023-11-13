package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDto;
import java.util.List;

public interface CommentService {

  CommentDto creatComment(Long id, CommentDto commentDto);
  List<CommentDto> getCommentsByPostId(long postId);

  CommentDto getCommentById(Long postId,Long commentId);

  CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);

  void deleteComment(Long postId, Long commentId);

}
