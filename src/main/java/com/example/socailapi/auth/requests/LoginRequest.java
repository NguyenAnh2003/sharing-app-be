package com.example.socailapi.auth.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String gmail, password;
}
