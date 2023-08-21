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
    private ObjectId userId;
    private ObjectId postId;

    /* read */
    public Like(String id, ObjectId userId, ObjectId postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    /* create */
    public Like(ObjectId userId, ObjectId postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
