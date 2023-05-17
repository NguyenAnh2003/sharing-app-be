package com.example.newsfeedapi.user.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest{
    private String gmail;
    private String password;
}
