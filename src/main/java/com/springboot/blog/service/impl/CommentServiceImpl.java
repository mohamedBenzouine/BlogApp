package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  private CommentRepository commentRepository;
  private PostRepository postRepository;

  public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
    this.commentRepository = commentRepository;
    this.postRepository = postRepository;
  }

  @Override
  public CommentDto creatComment(Long postId, CommentDto commentDto) {
    Comment comment = mapToEntity(commentDto);
    // retrieve (récuperer) post entity by id
    Post post = postRepository.findById(postId).orElseThrow(
        ()-> new ResourceNotFoundException("Post","id",postId)
    );

    //set post to comment entity
    comment.setPost(post);

    // save comment entity to Database
    Comment newComment = commentRepository.save(comment);

    return mapToDto(newComment);
  }

  private  CommentDto mapToDto(Comment comment){
    CommentDto commentDto = new CommentDto();
    commentDto.setId(comment.getId());
    commentDto.setName(comment.getName());
    commentDto.setBody(comment.getBody());
    commentDto.setEmail(comment.getEmail());
    return commentDto;
  }

  private Comment mapToEntity(CommentDto commentDto){
    Comment comment = new Comment();
    comment.setId(commentDto.getId());
    comment.setName(commentDto.getName());
    comment.setBody(commentDto.getBody());
    comment.setEmail(commentDto.getEmail());
    return comment;

  }


}
