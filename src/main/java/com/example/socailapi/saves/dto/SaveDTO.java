package com.example.socailapi.saves.dto;

import com.example.socailapi.post.dto.EmbeddedPost;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveDTO {
    private String id, userId;
    private EmbeddedPost post;
}
