package com.blog.commentservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_posted;
    @NotNull
    private int post_id;


}
