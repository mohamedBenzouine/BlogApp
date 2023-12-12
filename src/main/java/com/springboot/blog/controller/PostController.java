package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  private PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  // Creat blog post Api
  @PostMapping
  public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
    return  new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
  }

  // Get all posts rest api
  @GetMapping
  public PostResponse getAllPosts(
      @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
  ){
    return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
  }

  // Get post by id rest api
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
    return ResponseEntity.ok(postService.getPostById(id));

  }

  // Update post by id rest api
  @PutMapping("/{id}")
  public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
    PostDto postResponse = postService.updatePost(postDto, id);
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
  }
  // Delete post rst api
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id){
    postService.deletePostById(id);
    return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
  }

}
