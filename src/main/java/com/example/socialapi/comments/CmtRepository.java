package com.example.socialapi.comments;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CmtRepository extends MongoRepository<Comment, String> {
    Optional<List<Comment>> findAllByPostId(ObjectId postId);
}
