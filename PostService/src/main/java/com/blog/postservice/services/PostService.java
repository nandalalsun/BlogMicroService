package com.blog.postservice.services;

import com.blog.postservice.domain.Post;
import com.blog.postservice.dto.PostRequest;
import com.blog.postservice.dto.PostResponse;
import com.blog.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class PostService implements IPostService{

    private final PostRepository postRepository;

    private RestTemplate restTemplate;

    private final String userUrl = "http://localhost:8080/api/users/{id}";

    private final String pplUrl = "http://localhost:8080/api/users";

    private final String DELETE_COMMENT_ENDPOINT_URL = "http://localhost:8082/api/v1/comment/user/{id}";



    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Post newPost = Post.builder()
                .content(postRequest.getContent())
                .posted_date(postRequest.getPosted_date())
                .build();

        Post created = postRepository.save(newPost);
        return PostResponse.builder()
                .content(created.getContent())
                .posted_date(created.getPosted_date())
                .id(created.getId())
                .build();
    }

    @Override
    public PostResponse getPost(Long id) {
        Optional<Post> postFromDatabase = postRepository.findById(id);
        if(postFromDatabase.isPresent()){
            return PostResponse.builder()
                    .content(postFromDatabase.get().getContent())
                    .posted_date(postFromDatabase.get().getPosted_date())
                    .build();
        }
        return null;
    }

    @Override
    public List<PostResponse> getAllPost() {
        List<Post> postList = postRepository.findAll();
        List<PostResponse> postResponses = postList.stream().map(post -> {
            return PostResponse.builder()
                    .content(post.getContent())
                    .posted_date(post.getPosted_date())
                    .build();
        }).collect(Collectors.toList());
        return postResponses;
    }

    @Override
    public void deletePost(Long id) {
        Post postInDatabase = postRepository.getReferenceById(id);
        if(postInDatabase != null){
            deleteAllComments(id);
            postRepository.deleteById(id);
        }
    }

    @Override
    public List<PostResponse> findPostByUserId(Long id) {
        List<Post> postList = postRepository.findPostByUserId(id);
        return postList.stream().map(post -> {
            return PostResponse.builder()
                    .content(post.getContent())
                    .posted_date(post.getPosted_date())
                    .build();
        }).collect(Collectors.toList());
    }

    private void deleteAllComments(Long id){
        Map< String, Long> params = new HashMap< String, Long >();
        params.put("id", id);
        restTemplate.delete(DELETE_COMMENT_ENDPOINT_URL, params);
    }

}
