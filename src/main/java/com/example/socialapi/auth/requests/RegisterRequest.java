package com.example.socialapi.auth.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest{
    private String name;
    private String gmail;
    private String password;
}
