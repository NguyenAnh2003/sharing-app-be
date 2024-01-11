package com.example.socialapi.saves;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaveRepository extends MongoRepository<Save, String> {
    Optional<List<Save>> findAllByUserId(String userId);
    Optional<List<Save>> findAllByPostId(String postId);
    Optional<Save> findByPostIdAndUserId(String postId, String userId);
    Optional<Boolean> existsByPostIdAndUserId(String postId, String userId);
    Optional deleteByPostIdAndUserId(String postId, String userId);
}
