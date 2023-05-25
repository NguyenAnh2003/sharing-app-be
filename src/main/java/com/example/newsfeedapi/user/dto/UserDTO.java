package com.example.newsfeedapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO{
    private String id;
    private String name;
    private String gender;
    private String avatarURL;

    private LocalDateTime timestamp;
}
