package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
