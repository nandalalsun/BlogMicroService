package com.blog.postservice.repository;

import com.blog.postservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostByUserId(Long id);

}
