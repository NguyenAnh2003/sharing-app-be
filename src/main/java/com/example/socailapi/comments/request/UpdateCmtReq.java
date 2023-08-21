package com.example.socailapi.comments.request;

import lombok.Data;

@Data
public class UpdateCmtReq {
    private String userId, postId;
    private String content;
}
