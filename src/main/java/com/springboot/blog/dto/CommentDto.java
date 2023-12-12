package com.springboot.blog.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class CommentDto {

  private Long id;

  // name should not be null or empty
  @NotEmpty(message = "Name should not be nul or empty")
  private String name;
  // email should not be null or empty
  // email field validation
  @NotEmpty(message = "Email should not be nul or empty")
  @Email
  private String email;
  // Comment body should not be null or empty
  // Comment body must be minimun 10 characters
  @NotEmpty(message = "Comment body should not be null or empty")
  @Size(min = 10, message = "Comment body must be minimun 10 characters")
  private String body;
}
