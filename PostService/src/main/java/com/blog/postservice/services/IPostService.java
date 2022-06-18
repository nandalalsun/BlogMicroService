package com.blog.postservice.services;

import com.blog.postservice.dto.PostRequest;
import com.blog.postservice.dto.PostResponse;
import com.blog.postservice.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {
    public abstract PostResponse createPost(PostRequest postRequest);
    public abstract PostResponse getPost(Long id);
    public abstract List<PostResponse> getAllPost();
    public abstract void deletePost(Long id);

    public abstract List<PostResponse> findPostByUserId(Long id);
}
