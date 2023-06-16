package com.example.newsfeedapi.post;

import lombok.*;
import org.bson.types.ObjectId;
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
    private ObjectId userId;
    private ObjectId categoryId;
    // object embedded data user
    // Denormalization
    @Indexed(unique = true)
    private String title;
    private String description;
    //
    private String imageURL;
    private LocalDateTime timestamp;

    public Post(ObjectId userId, ObjectId cateId, String title, String description, String imageURL, LocalDateTime timestamp) {
        this.userId = userId;
        this.categoryId = cateId;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.timestamp = timestamp;
    }

    public Post(String id, ObjectId userId, ObjectId cateId, String title, String description, String imageURL, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.categoryId = cateId;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.timestamp = timestamp;
    }
}
