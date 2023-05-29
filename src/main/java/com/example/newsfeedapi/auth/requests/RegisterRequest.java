package com.example.newsfeedapi.auth.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest{
    private String name;
    private String gmail;
    private String password;
    private String avatarURL;
    private String gender;

}
