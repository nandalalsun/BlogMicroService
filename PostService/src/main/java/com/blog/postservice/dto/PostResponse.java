package com.blog.postservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private int id;
    private String content;
    private Date posted_date;
    private int user_id;
}
