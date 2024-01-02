package com.example.socialapi.follow;

import com.example.socialapi.follow.dto.FollowDTO;
import com.example.socialapi.follow.dto.FollowDTOMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final FollowDTOMapper mapper;

    /* create follow */
    public FollowDTO startFollowUser(String userId, String followingUserId) {
        Follow object = new Follow(new ObjectId(userId),
                new ObjectId(followingUserId), LocalDateTime.now());
        return mapper.apply(followRepository.save(object));
    }
    /* get follow by userId */
    public List<FollowDTO> getFollowingUsers(String userId) {
        return followRepository
                .findAllByFollowerId(new ObjectId(userId)).orElseThrow()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
    /* delete follow */
    public boolean deleteFollowingUser(String userId, String followingUserId) {
        followRepository.deleteByFollowerIdAndFollowingId(new ObjectId(userId),
                                                          new ObjectId(followingUserId));
        boolean exist = followRepository.existsByFollowerIdAndFollowingId(new ObjectId(userId),
                                                            new ObjectId(followingUserId)).orElseThrow();
        return exist;
    }
}
