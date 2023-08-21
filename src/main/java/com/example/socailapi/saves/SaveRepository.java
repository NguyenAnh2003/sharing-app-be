package com.example.socailapi.saves;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaveRepository extends MongoRepository<Save, String> {
    Optional<List<Save>> findAllByPostId(ObjectId postId);
    Optional<Save> findByPostIdAndUserId(ObjectId postId, ObjectId userId);
    Optional<Boolean> existsByPostIdAndUserId(ObjectId postId, ObjectId userId);
    Optional deleteByPostIdAndUserId(ObjectId postId, ObjectId userId);
}
