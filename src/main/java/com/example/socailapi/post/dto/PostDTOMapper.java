package com.example.socailapi.post.dto;

import com.example.socailapi.post.Post;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostDTOMapper implements Function<Post, PostDTO> {
    @Override
    public PostDTO apply(Post post) {
        return new PostDTO(post.getId(),
                post.getUser(),
                post.getCategory(),
                post.getTitle(), post.getDescription(),
                post.getImageURL(), post.getTimestamp());
    }

    @Override
    public <V> Function<V, PostDTO> compose(Function<? super V, ? extends Post> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Post, V> andThen(Function<? super PostDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
