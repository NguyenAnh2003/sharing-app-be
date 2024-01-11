package com.example.socialapi.likes;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Like {
    @Id
    private String id;
    /* userId, categoryId */
    private String userId;
    private String postId;

    /* read */
    public Like(String id, String userId, String postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    /* create */
    public Like(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
