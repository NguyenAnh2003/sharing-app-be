package com.example.socialapi.comments.request;

import lombok.Data;

@Data
public class UpdateCommentReq {
    private String userId, postId;
    private String content;
}
