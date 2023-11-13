package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
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

  @Override
  public List<CommentDto> getCommentsByPostId(long postId) {
    // retrieve comment by postId
    List<Comment> comments = commentRepository.findByPostId(postId);

    //convert
    return comments.stream()
        .map(comment -> mapToDto(comment))
        .collect(Collectors.toList());
  }

  @Override
  public CommentDto getCommentById(Long postId, Long commentId) {
    // retrieve (récuperer) post entity by id
    Post post = postRepository.findById(postId).orElseThrow(
        ()-> new ResourceNotFoundException("Post","id",postId)
    );
    // retrieve comment by id
    Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
            new ResourceNotFoundException("Commebt", "id", commentId));
    if (!comment.getPost().getId().equals(post.getId())){
      throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
    }
    return mapToDto(comment);
  }

  @Override
  public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
    // retrieve (récuperer) post entity by id
    Post post = postRepository.findById(postId).orElseThrow(
        ()-> new ResourceNotFoundException("Post","id",postId)
    );
    // retrieve comment by id
    Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
        new ResourceNotFoundException("Commebt", "id", commentId));

    if(!comment.getPost().getId().equals(post.getId())){
      throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to Post");
    }

    comment.setName(commentRequest.getName());
    comment.setEmail(commentRequest.getEmail());
    comment.setBody(commentRequest.getBody());
    Comment updatedComment = commentRepository.save(comment);
    return mapToDto(updatedComment);
  }

  @Override
  public void deleteComment(Long postId, Long commentId) {
    // retrieve (récuperer) post entity by id
    Post post = postRepository.findById(postId).orElseThrow(
        ()-> new ResourceNotFoundException("Post","id",postId)
    );
    // retrieve comment by id
    Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
        new ResourceNotFoundException("Commebt", "id", commentId));

    if(!comment.getPost().getId().equals(post.getId())){
      throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to Post");
    }
    commentRepository.delete(comment);
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
