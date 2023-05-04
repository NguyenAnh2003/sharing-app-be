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
    @Indexed(unique = true)
    private String gmail;
    private String avatarURL;
    private Gender gender;
    // favorites ?
    private ArrayList<String> wishlist;

    public User(String name, String gmail, String avatarURL, Gender gender, ArrayList<String> wishlist) {
        this.name = name;
        this.gmail = gmail;
        this.avatarURL = avatarURL;
        this.gender = gender;
        this.wishlist = wishlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ArrayList<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(ArrayList<String> wishlist) {
        this.wishlist = wishlist;
    }
}
