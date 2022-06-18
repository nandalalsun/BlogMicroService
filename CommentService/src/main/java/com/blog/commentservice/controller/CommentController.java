package com.blog.commentservice.controller;

import com.blog.commentservice.domain.Comment;
import com.blog.commentservice.dto.CommentRequest;
import com.blog.commentservice.dto.CommentResponse;
import com.blog.commentservice.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/")
    public ResponseEntity<?> getAllComments(){
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable Long id){
        CommentResponse commentResponse = commentService.getComment(id);

        if(commentResponse != null){
            return new ResponseEntity<>(commentResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("No comment found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getCommentByPostId(@PathVariable Long id){
        List<CommentResponse> commentResponseList = commentService.getAllCommentByPostId(id);
        return new ResponseEntity<>(commentResponseList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest){
        CommentResponse commentResponse = commentService.createComment(commentRequest);
        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@RequestBody CommentRequest commentRequest, @PathVariable Long id){
        CommentResponse isCommentExist = commentService.getComment(id);
        if(isCommentExist != null){
            CommentResponse commentResponse = commentService.updateComment(commentRequest, id);
            return new ResponseEntity<>(commentResponse, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("User not exist", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteCommentByUserId(@PathVariable Long id){

        commentService.deleteCommentByUserId(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
