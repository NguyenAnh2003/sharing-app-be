package com.example.socialapi.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDTO{
    private String id;
    private String name;
    private String avatarURL;
    private LocalDateTime timestamp;
}
