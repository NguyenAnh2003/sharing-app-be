package com.example.socailapi.comments.request;

import lombok.Data;

@Data
public class CreateCmtReq {
    private String userId, postId;
    private String content;
}
