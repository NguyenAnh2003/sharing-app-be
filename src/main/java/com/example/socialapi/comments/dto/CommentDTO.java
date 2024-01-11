package com.example.socialapi.comments.dto;

import com.example.socialapi.user.dto.EmbeddedUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentDTO {
    private String id, content;
    private String userId;
    private String postId;
    private LocalDateTime timestamp;
}
