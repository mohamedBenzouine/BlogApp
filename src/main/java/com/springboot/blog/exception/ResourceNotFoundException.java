package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException {

  private String resourceName;
  private String fiieldName;
  private Long fieldValue;

  public ResourceNotFoundException(String resourceName, String fiieldName, Long fieldValue) {
    super(String.format("%s not found with %s : %s", resourceName, fiieldName, fieldValue)); // Post not found with id: 1
    this.resourceName = resourceName;
    this.fiieldName = fiieldName;
    this.fieldValue = fieldValue;
  }

  public String getResourceName() {
    return resourceName;
  }

  public String getFiieldName() {
    return fiieldName;
  }

  public Long getFieldValue() {
    return fieldValue;
  }


}
