package com.example.socialapi.follow;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document
public class Follow {
    @Id
    private String id;
    /* userId ~ current user */
    private String followerId;
    /* userId are followed */
    private String followingId;
    private LocalDateTime timestamp;

    public Follow(String userId, String followingUserId, LocalDateTime timestamp) {
        this.followerId = userId;
        this.followingId = followingUserId;
        this.timestamp = timestamp;
    }

    public Follow(String id, String userId, String followingUserId) {
        this.id = id;
        this.followerId = userId;
        this.followingId = followingUserId;
    }
}

