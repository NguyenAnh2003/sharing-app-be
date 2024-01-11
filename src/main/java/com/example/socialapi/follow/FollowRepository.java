package com.example.socialapi.follow;

import com.example.socialapi.follow.dto.FollowDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends MongoRepository<Follow, String> {
    Optional<Boolean> existsByFollowerIdAndFollowingId(String userId, String followingUserId);
    Optional<List<Follow>> findAllByFollowerId(String userId);
    Optional<Follow> deleteByFollowerIdAndFollowingId(String userId, String followingId);
}
