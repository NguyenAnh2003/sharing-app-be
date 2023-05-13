package com.example.newsfeedapi.user.requests;

public record LoginRequest(
        String email,
        String password
) {
}
