package com.example.newsfeedapi.post.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class EmbeddedPost {
    @Id
    private String id;
    private String title;
    private String imageURL;
}
