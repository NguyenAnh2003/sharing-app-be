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
    private EmbeddedPost post;
    private ObjectId userId;

    public Save(String id, EmbeddedPost post, ObjectId userId) {
        this.id = id;
        this.post = post;
        this.userId = userId;
    }

    public Save(EmbeddedPost post, ObjectId userId) {
        this.post = post;
        this.userId = userId;
    }

}
