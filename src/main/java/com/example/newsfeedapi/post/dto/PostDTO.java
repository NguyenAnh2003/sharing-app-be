package com.example.newsfeedapi.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostDTO {
    private String id, userId, categoryId;
    /* Denormalization */
    private String title, description;
    private String imageURL;
    private LocalDateTime timestamp;
}
