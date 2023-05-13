package com.example.newsfeedapi.user.dto;

public record UserDTO(
    String id,
    String name,
    String email,
    String gender,
    String avatarURL
) {

}
