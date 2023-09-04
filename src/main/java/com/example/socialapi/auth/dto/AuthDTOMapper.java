package com.example.socialapi.auth.dto;

import com.example.socialapi.user.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class AuthDTOMapper implements Function<User, AuthDTO> {
    @Override
    public AuthDTO apply(User user) {
        return new AuthDTO(user.getId());
    }
}
