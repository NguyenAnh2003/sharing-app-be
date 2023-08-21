package com.example.socailapi.post.requests;

import lombok.Data;

@Data
public class UpdatePostReq {
    // userId will constant
    private String userId, categoryId;
    private String title, description;
    private String imageURL;
}
