package com.example.newsfeedapi.likes.dto;

import com.example.newsfeedapi.likes.Like;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class LikeDTOMapper implements Function<Like, LikeDTO> {

    @Override
    public LikeDTO apply(Like like) {
        return new LikeDTO(like.getId(),
                like.getUserId().toString(),
                like.getPostId().toString());
    }
}
