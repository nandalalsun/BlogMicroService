package com.blog.commentservice.repository;

import com.blog.commentservice.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public abstract Comment findByUserId(Long id);

}
