package com.example.newsfeedapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO{
    private String id;
    private String name;
    private String gender;
    private String avatarURL;
}
