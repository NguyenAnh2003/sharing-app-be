package com.example.socialapi.follow.dto;

import com.example.socialapi.follow.Follow;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class FollowDTOMapper implements Function<Follow, FollowDTO> {

    @Override
    public FollowDTO apply(Follow follow) {
        return new FollowDTO(follow.getId(),
                follow.getFollowerId(),
                follow.getFollowingId());
    }
}
