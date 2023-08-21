package com.example.socialapi.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class EmbeddedPost {
    @Id
    private String id;
    private String title;
    private String description;
    private String imageURL;
}
