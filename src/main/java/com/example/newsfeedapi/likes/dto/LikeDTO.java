package com.example.newsfeedapi.likes.dto;

import lombok.Data;

@Data
public class LikeDTO {
    private String id, userId, postId;
}
