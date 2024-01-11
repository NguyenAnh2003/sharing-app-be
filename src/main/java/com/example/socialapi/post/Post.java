package com.example.socialapi.post;

import com.example.socialapi.category.dto.EmbeddedCategory;
import com.example.socialapi.user.dto.EmbeddedUser;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class Post {
    @Id
    private String id;
    /* userId,  categoryId */
    private String userId;
    private EmbeddedCategory category;
    // object embedded data user
    // Denormalization
    @Indexed(unique = true)
    private String title;
    private String description;
    //
    private String imageURL;
    private LocalDateTime timestamp;

    public Post(String userId, EmbeddedCategory category, String title, String description, String imageURL, LocalDateTime timestamp) {
        this.userId = userId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.timestamp = timestamp;
    }

    public Post(String id, String userId, EmbeddedCategory category, String title, String description, String imageURL, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.timestamp = timestamp;
    }
}
