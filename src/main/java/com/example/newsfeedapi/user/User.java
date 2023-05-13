package com.example.newsfeedapi.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document
public class User {
    @Id
    private String id;
    private String name;

    // find used to login in Mongo?
    @Indexed(unique = true)
    private String gmail;
    // Password reveal in DB
    @Indexed(unique = true)
    private String password;

    private String avatarURL;

    private String gender;
    // favorites ?

    public User(String name, String gmail, String password, String avatarURL, String gender) {
        this.name = name;
        this.gmail = gmail;
        this.password = password;
        this.avatarURL = avatarURL;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
