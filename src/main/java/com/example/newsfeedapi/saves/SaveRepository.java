package com.example.newsfeedapi.saves;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveRepository extends MongoRepository<Save, String> {

}
