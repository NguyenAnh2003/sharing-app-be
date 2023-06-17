package com.example.newsfeedapi.saves;

import com.example.newsfeedapi.post.dto.EmbeddedPost;
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

}
