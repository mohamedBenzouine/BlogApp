package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// No need to annotate @Repository, because its already annotated int the top of SimpleJpaRepo class
// that implements JpaRepoImpl interface that extends JpaRepository
public interface CommentRepository extends JpaRepository<Comment,Long> {

  List<Comment> findByPostId(long postId);


}
