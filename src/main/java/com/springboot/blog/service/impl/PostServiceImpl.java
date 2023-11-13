package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

  private ModelMapper mapper;

  public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {

    this.postRepository = postRepository;
    this.mapper = mapper;
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

    PostDto postDto = mapper.map(post,PostDto.class);

    /*PostDto postDto = new PostDto();
    postDto.setId(post.getId());
    postDto.setTitle(post.getTitle());
    postDto.setDescription(post.getDescription());
    postDto.setContent(post.getContent());*/

    return postDto;
  }

  //Convert DTO into Entity
  private Post mapToEntity(PostDto postDto){
    Post post = mapper.map(postDto, Post.class);

    /*Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());*/
    return post;
  }

  @Override
  public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir) {

    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
        :Sort.by(sortBy).descending();

    // create Pageable instance
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    Page<Post> posts =postRepository.findAll(pageable);

    // get content for page object
    List<Post> listOfPosts = posts.getContent();
    List<PostDto> content =  listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    PostResponse postResponse = new PostResponse();
    postResponse.setContent(content);
    postResponse.setPageNo(posts.getNumber());
    postResponse.setPageSize(posts.getSize());
    postResponse.setTotalElements(posts.getTotalElements());
    postResponse.setTotalPages(posts.getTotalPages());
    postResponse.setLast(posts.isLast());
    return postResponse;

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
