package com.example.newsfeedapi.user.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {
    String name;
    String gender;
    String avatarURL;
}
