package com.springboot.blog.dto;

import java.util.Set;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

  private Long id;

  // title should not be empty or null
  // title should have at least 2 characters
  @NotEmpty
  @Size(min = 2 , message = "Post title should have at least 2 characters")
  private String title;

  // description should not be empty or null
  // description should have at least 10 characters
  @NotEmpty
  @Size(min = 10, message = "ost description should have at least 10 characters")
  private String description;

  // content should not be empty or null
  @NotEmpty
  private String content;
  private Set<CommentDto> comments;

}
