package com.example.socialapi.likes.request;

import lombok.Data;

@Data
public class LikeReq {
    private String userId, postId;
}
