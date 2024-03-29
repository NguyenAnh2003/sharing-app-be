package com.example.socialapi.likes.dto;

import com.example.socialapi.likes.Like;
import org.springframework.stereotype.Service;
import java.util.function.Function;
@Service
public class LikeDTOMapper implements Function<Like, LikeDTO> {

    @Override
    public LikeDTO apply(Like like) {
        return new LikeDTO(like.getId(),
                like.getUserId(),
                like.getPostId());
    }
}
