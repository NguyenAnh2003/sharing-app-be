package com.example.socialapi.follow;

import com.example.socialapi.follow.dto.FollowDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends MongoRepository<Follow, String> {
    Optional<Boolean> existsByFollowerIdAndFollowingId(ObjectId userId, ObjectId followingUserId);
    Optional<List<Follow>> findAllByFollowerId(ObjectId userId);
    Optional<Follow> deleteByFollowerIdAndFollowingId(ObjectId userId, ObjectId followingId);
}
