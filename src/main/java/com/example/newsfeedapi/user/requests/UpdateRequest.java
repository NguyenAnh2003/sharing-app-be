package com.example.newsfeedapi.user.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UpdateRequest {
    private String name;
    private String gender;
}
