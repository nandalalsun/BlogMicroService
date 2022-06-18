package com.blog.postservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PostServiceApplication {

    @Bean
    public RestTemplate getRestTemplet(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(PostServiceApplication.class, args);
    }

}
