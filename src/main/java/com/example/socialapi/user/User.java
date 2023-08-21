package com.example.socialapi.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@NoArgsConstructor
@Data
@Document
public class User implements UserDetails {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String gmail;
    private String password;
    private String avatarURL;
    private String gender;

    // timestamp most important?
    private LocalDateTime timestamp;

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

    /* return role of specific user? */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return gmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

