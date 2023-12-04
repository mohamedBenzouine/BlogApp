package com.springboot.blog.dto;

import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
