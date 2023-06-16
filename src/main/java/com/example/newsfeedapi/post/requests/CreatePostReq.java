package com.example.newsfeedapi.post.requests;

import lombok.Data;

@Data
public class CreatePostReq {
    private String userId, categoryId;
    private String title, description;
    private String imageURL;

}
