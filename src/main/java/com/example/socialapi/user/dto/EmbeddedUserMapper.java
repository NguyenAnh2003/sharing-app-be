package com.example.socialapi.user.dto;

import com.example.socialapi.user.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class EmbeddedUserMapper implements Function<User, EmbeddedUser> {

    @Override
    public EmbeddedUser apply(User user) {
        return new EmbeddedUser(
                user.getId(),
                user.getName(),
                user.getAvatarURL()
        );
    }

    @Override
    public <V> Function<V, EmbeddedUser> compose(Function<? super V, ? extends User> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<User, V> andThen(Function<? super EmbeddedUser, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
