package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import java.util.List;

public interface PostService {

  PostDto createPost(PostDto postDto);

  List<PostDto> getAllPosts();

  PostDto getPostById (Long id);



}
