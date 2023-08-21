package com.example.socialapi.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    /* search post by title */

    /* find by userId */
    Optional<List<Post>> findAllByUserId(String userId);
}
