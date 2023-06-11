package com.example.newsfeedapi.post;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Post {
    @Id
    private String id;
    /* userId, destId, categoryId */
    @Indexed(unique = true)
    private String title;
    private String description;
    //
    private String imageURL;
    private PostMode mode;
    private LocalDateTime timestamp;

}
