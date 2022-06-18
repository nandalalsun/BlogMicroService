package com.blog.postservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    private int id;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date posted_date;
    @NotNull
    private int user_id;

}
