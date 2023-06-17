package com.example.newsfeedapi.post;

import com.example.newsfeedapi.category.dto.EmbeddedCategory;
import com.example.newsfeedapi.user.dto.EmbeddedUser;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class Post {
    @Id
    private String id;
    /* userId,  categoryId */
    private EmbeddedUser user;
    private EmbeddedCategory category;
    // object embedded data user
    // Denormalization
    @Indexed(unique = true)
    private String title;
    private String description;
    //
    private String imageURL;
    private LocalDateTime timestamp;

    public Post(EmbeddedUser user, EmbeddedCategory category, String title, String description, String imageURL, LocalDateTime timestamp) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.timestamp = timestamp;
    }

    public Post(String id, EmbeddedUser user, EmbeddedCategory category, String title, String description, String imageURL, LocalDateTime timestamp) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.timestamp = timestamp;
    }
}
