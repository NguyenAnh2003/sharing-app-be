package com.example.newsfeedapi.comments.dto;

import com.example.newsfeedapi.user.dto.EmbeddedUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CmtDTO {
    private String id, content;
    private EmbeddedUser user;
    private String postId;
}
