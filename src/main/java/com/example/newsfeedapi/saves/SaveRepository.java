package com.example.newsfeedapi.saves;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaveRepository extends MongoRepository<Save, String> {
    Optional<Save> findAllByPostId(String postId);
    Optional<Save> findByPostIdAndUserId(String postId, String userId);
    Optional<Boolean> existsByPostIdAndUserId(String postId, String userId);
}
