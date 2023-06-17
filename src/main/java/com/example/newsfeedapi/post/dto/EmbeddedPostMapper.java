package com.example.newsfeedapi.post.dto;

import com.example.newsfeedapi.post.Post;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmbeddedPostMapper implements Function<Post, EmbeddedPost> {

    @Override
    public EmbeddedPost apply(Post post) {
        return null;
    }

    @Override
    public <V> Function<V, EmbeddedPost> compose(Function<? super V, ? extends Post> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Post, V> andThen(Function<? super EmbeddedPost, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
