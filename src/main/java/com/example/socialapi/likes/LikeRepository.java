package com.example.socialapi.likes;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {
    Optional<Like> findByPostIdAndUserId(ObjectId postId, ObjectId userId);
    Optional<Boolean> existsByPostIdAndUserId(ObjectId postId, ObjectId userId);
    Optional<List<Like>> findAllByPostId(ObjectId postId);
    Optional deleteByPostIdAndUserId(ObjectId postId, ObjectId userId);
}
