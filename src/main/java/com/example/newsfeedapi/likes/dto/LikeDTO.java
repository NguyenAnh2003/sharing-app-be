package com.example.newsfeedapi.likes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeDTO {
    private String id, userId, postId;
}
