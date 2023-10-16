package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.service.PostService;
import java.util.List;
import javafx.geometry.Pos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  // Creat blog post Api

  @PostMapping
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
    return  new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
  }

  // get all posts rest api
  @GetMapping
  public List<PostDto> getAllPosts(){
    return postService.getAllPosts();
  }

  // get post by ID

  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id){
    return ResponseEntity.ok(postService.getPostById(id));

  }
}
