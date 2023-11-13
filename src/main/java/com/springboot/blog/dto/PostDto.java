package com.springboot.blog.dto;

import java.util.Set;
import lombok.Data;

@Data
public class PostDto {

  private Long id;
  private String title;
  private String description;
  private String content;
  private Set<CommentDto> comments;

}
