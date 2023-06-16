package com.example.newsfeedapi.post.dto;

import com.example.newsfeedapi.post.Post;

import java.util.function.Function;

public class PostDTOMapper implements Function<Post, PostDTO> {
    @Override
    public PostDTO apply(Post post) {
        return new PostDTO(post.getId(),
                post.getUserId().toString(),
                post.getCategoryId().toString(),
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
