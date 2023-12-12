package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String body;

  @ManyToOne(fetch = FetchType.LAZY)  //LAZY tells Hiber to only fetch the related entities from DB when you use relationship
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

}
