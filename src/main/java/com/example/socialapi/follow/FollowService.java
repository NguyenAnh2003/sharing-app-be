package com.example.socialapi.follow;

import com.example.socialapi.follow.dto.FollowDTO;
import com.example.socialapi.follow.dto.FollowDTOMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logging = LoggerFactory.getLogger(FollowService.class);

    /* create follow */
    public FollowDTO startFollowUser(String userId, String followingUserId) {
        try {
            logging.info("create follow record service class");
            logging.debug("debugging create follow record service class");
            Follow object = new Follow(new ObjectId(userId),
                    new ObjectId(followingUserId), LocalDateTime.now());
            return mapper.apply(followRepository.save(object));
        } catch (Exception e) {
            logging.error("Internal error cannot create follow record");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    /* get follow by userId */
    public List<FollowDTO> getFollowingUsers(String userId) {
        try {
            logging.info("get following users service class");
            logging.debug("debugging get all following users by userId");
            return followRepository
                    .findAllByFollowerId(new ObjectId(userId)).orElseThrow()
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logging.error("cannot get all following users by userId");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    /* delete follow */
    public Boolean deleteFollowingUser(String userId, String followingUserId) {
        try {
            logging.debug("debugging deleting following user");
            followRepository.deleteByFollowerIdAndFollowingId(new ObjectId(userId),
                    new ObjectId(followingUserId));
            return followRepository.existsByFollowerIdAndFollowingId(new ObjectId(userId),
                    new ObjectId(followingUserId)).orElseThrow();
        } catch (Exception e) {
            logging.error("cannot unfollow user");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }
}
