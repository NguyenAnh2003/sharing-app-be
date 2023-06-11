package com.example.newsfeedapi.category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CateRepository extends MongoRepository<Category, String> {
    /* Create */
    /* getAll */

    /* GetCateById - Optional */

}
