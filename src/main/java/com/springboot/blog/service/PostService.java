package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.entity.Post;
import java.util.List;
import org.hibernate.persister.entity.Loadable;

public interface PostService {

  PostDto createPost(PostDto postDto);
  PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);
  PostDto getPostById (Long id);
  PostDto updatePost(PostDto postDto, Long id);

  void deletePostById(Long id);



}
