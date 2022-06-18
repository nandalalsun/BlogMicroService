package com.blog.commentservice.services;

import com.blog.commentservice.domain.Comment;
import com.blog.commentservice.dto.CommentRequest;
import com.blog.commentservice.dto.CommentResponse;
import com.blog.commentservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{
    private final RestTemplate restTemplate;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponse getComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            Comment c = comment.get();

            CommentResponse commentResponse = CommentResponse.builder()
                    .content(c.getContent())
                    .date_posted(c.getDate_posted())
                    .build();
        }
        return null;
    }

    @Override
    public List<CommentResponse> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();

        return commentList.stream().map(comment-> {
            return CommentResponse.builder()
                    .content(comment.getContent())
                    .date_posted(comment.getDate_posted())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getAllCommentByPostId(Long id) {
        //TODO: make a repository to get data by user
        Comment inTheDatabase = commentRepository.findByUserId(id);
        return null;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {
        Comment newComment = Comment.builder()
                .content(commentRequest.getContent())
                .date_posted(commentRequest.getDate_posted())
                .post_id(commentRequest.getPost_id())
                .build();

        Comment createdComment = commentRepository.save(newComment);

        return CommentResponse.builder()
                .content(createdComment.getContent())
                .date_posted(createdComment.getDate_posted())
                .post_id(createdComment.getPost_id())
                .build();
    }

    @Override
    public CommentResponse updateComment(CommentRequest commentRequest, Long id) {
        Comment newComment = Comment.builder()
                .content(commentRequest.getContent())
                .date_posted(commentRequest.getDate_posted())
                .post_id(commentRequest.getPost_id())
                .id(id)
                .build();

        Comment updatedComment = commentRepository.save(newComment);

        return CommentResponse.builder()
                .content(updatedComment.getContent())
                .date_posted(updatedComment.getDate_posted())
                .post_id(updatedComment.getPost_id())
                .build();
    }


}
