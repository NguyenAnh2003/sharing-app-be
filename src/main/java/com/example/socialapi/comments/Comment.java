package com.example.socialapi.comments;

import com.example.socialapi.user.dto.EmbeddedUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@NoArgsConstructor
public class Comment {
    @Id
    private String id;
    private String postId;
    private String userId;
    private String content;
    private LocalDateTime timestamp;

    public Comment(String id, String userId, String postId, String content, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
    }
    public Comment(String userId, String postId, String content, LocalDateTime timestamp) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
    }
}
