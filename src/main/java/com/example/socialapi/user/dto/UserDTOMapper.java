package com.example.socialapi.user.dto;

import com.example.socialapi.user.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getAvatarURL(),
                user.getTimestamp()
        );
    }

    @Override
    public <V> Function<V, UserDTO> compose(Function<? super V, ? extends User> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<User, V> andThen(Function<? super UserDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
