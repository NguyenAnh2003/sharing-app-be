package com.example.socailapi.comments;

import com.example.socailapi.user.dto.EmbeddedUser;
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
    private ObjectId postId;
    private EmbeddedUser user;
    private String content;
    private LocalDateTime timestamp;

    public Comment(String id, EmbeddedUser user, ObjectId postId, String content, LocalDateTime timestamp) {
        this.id = id;
        this.user = user;
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
    }
    public Comment(EmbeddedUser user, ObjectId postId, String content, LocalDateTime timestamp) {
        this.user = user;
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
    }
}
