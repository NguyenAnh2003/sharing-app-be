package com.example.socialapi.saves.dto;

import com.example.socialapi.post.dto.EmbeddedPost;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveDTO {
    private String id, userId, postId;
}
