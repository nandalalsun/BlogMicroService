package com.blog.postservice.controller;

import com.blog.postservice.dto.PostRequest;
import com.blog.postservice.dto.PostResponse;
import com.blog.postservice.services.IPostService;
import com.blog.postservice.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController  {

    private final PostService postService;


    @GetMapping("")
    public PostResponse createPost(PostRequest postRequest) {
        return postService.createPost(postRequest);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        PostResponse postResponse = postService.getPost(id);
        if(postResponse != null){
            return new ResponseEntity<>(postResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    }


    public List<PostResponse> getAllPost() {
        return postService.getAllPost();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        PostResponse postResponse = postService.getPost(id);
        if(postResponse != null){
            postService.deletePost(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PostResponse> findPostByUserId(Long id) {
        return postService.findPostByUserId(id);
    }
}
