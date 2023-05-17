package com.example.newsfeedapi.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document
public class User {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String gmail;
    private String password;
    private String avatarURL;
    private String gender;

    public User(String name, String gmail, String password, String gender, String avatarURL) {
        this.name = name;
        this.gmail = gmail;
        this.password = password;
        this.gender = gender;
        this.avatarURL = avatarURL;
    }

    public User(String id, String name, String gender, String avatarURL) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.avatarURL = avatarURL;
    }

}
