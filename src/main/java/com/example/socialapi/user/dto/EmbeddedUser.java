package com.example.socialapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class EmbeddedUser {
    @Id
    private String id;
    private String name;
    private String avatarURL;
}
