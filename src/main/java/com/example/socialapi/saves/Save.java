package com.example.socialapi.saves;

import com.example.socialapi.post.dto.EmbeddedPost;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class Save {
    @Id
    private String id;
    private String postId;
    private String userId;

    public Save(String id, String postId, String userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    public Save(String postId, String userId) {
        this.postId = postId;
        this.userId = userId;
    }

}
