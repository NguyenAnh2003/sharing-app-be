package com.example.newsfeedapi.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Document
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String gmail;
    private String password;
    private String avatarURL;
    private String gender;

    // timestamp most important?
    LocalDateTime timestamp;

    public User(String name, String gmail, String password, String gender, String avatarURL, LocalDateTime timestamp) {
        this.name = name;
        this.gmail = gmail;
        this.password = password;
        this.gender = gender;
        this.avatarURL = avatarURL;
        this.timestamp = timestamp;
    }

    public User(String id, String name, String gender, String avatarURL, LocalDateTime timestamp) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.avatarURL = avatarURL;
        this.timestamp = timestamp;
    }

}
