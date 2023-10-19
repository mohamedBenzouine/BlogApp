package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public PostDto createPost(PostDto postDto) {

    //Convert DTO into Entity
    Post post = mapToEntity(postDto);
    Post interPost = postRepository.save(post);

    // convert Entity ento DTO
    PostDto postResponse = mapToDto(interPost);
    return postResponse;
  }

  // convert Entity ento DTO
  private PostDto mapToDto (Post post){
    PostDto postDto = new PostDto();
    postDto.setTitle(post.getTitle());
    postDto.setDescription(post.getDescription());
    postDto.setContent(post.getContent());
    return postDto;
  }

  //Convert DTO into Entity
  private Post mapToEntity(PostDto postDto){
    Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(post.getContent());
    return post;
  }

  @Override
  public List<PostDto> getAllPosts() {
    List<Post> posts =postRepository.findAll();
    return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
  }

  @Override
  public PostDto getPostById(Long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));

    return mapToDto(post);
  }

  @Override
  public PostDto updatePost(PostDto postDto, Long id) {
    // get post by id from the database
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id", id));
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());
    Post updatePost = postRepository.save(post);

    return mapToDto(updatePost);
  }

  @Override
  public void deletePostById(Long id) {
    // get post by id from the database
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id", id));
    postRepository.delete(post);
  }
  /*
   @Override
  public PostDto convertEntityToDto(Post post) {
    PostDto postDto = new PostDto();
    postDto.setTitle(post.getTitle());
    postDto.setDescription(post.getDescription());
    postDto.setContent(post.getContent());
    return postDto;
  }

  @Override
  public Post convertDtoToEntity(PostDto postDto) {
    Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());
    return post;
  }
  */

}
