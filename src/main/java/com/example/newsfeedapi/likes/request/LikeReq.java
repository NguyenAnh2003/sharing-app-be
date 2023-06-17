package com.example.newsfeedapi.likes.request;

import lombok.Data;

@Data
public class LikeReq {
    private String userId, postId;
}
