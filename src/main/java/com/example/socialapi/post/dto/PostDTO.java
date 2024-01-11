package com.example.socialapi.post.dto;

import com.example.socialapi.category.dto.EmbeddedCategory;
import com.example.socialapi.user.dto.EmbeddedUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostDTO {
    private String id;
    /* Denormalization */
    private String userId;
    private EmbeddedCategory category;
    private String title, description;
    private String imageURL;
    private LocalDateTime timestamp;
}
