package com.example.newsfeedapi.saves.dto;

import com.example.newsfeedapi.post.dto.EmbeddedPost;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveDTO {
    private String id, userId;
    private EmbeddedPost post;
}
