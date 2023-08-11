package com.example.newsfeedapi.follow;

import lombok.Data;
import lombok.NoArgsConstructor;
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
}
