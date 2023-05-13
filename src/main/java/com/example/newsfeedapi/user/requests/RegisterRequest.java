package com.example.newsfeedapi.user.requests;

public record RegisterRequest(
        String name,
        String email,
        String password,
        String avatarURL,
        String gender
){
}
