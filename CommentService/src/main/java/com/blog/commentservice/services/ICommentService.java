package com.blog.commentservice.services;

import com.blog.commentservice.domain.Comment;
import com.blog.commentservice.dto.CommentRequest;
import com.blog.commentservice.dto.CommentResponse;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    public abstract CommentResponse getComment(Long id);
    public abstract List<CommentResponse> getAllComments();
    public abstract List<CommentResponse> getAllCommentByPostId(Long id);
    public abstract void deleteComment(Long id);
    public abstract CommentResponse createComment(CommentRequest commentRequest);
    public abstract CommentResponse updateComment(CommentRequest commentRequest, Long id);

}
