package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDto;
import java.util.List;

public interface CommentService {

  CommentDto creatComment(Long id, CommentDto commentDto);

  List<CommentDto> getCommentsByPostId(long postId);

}
