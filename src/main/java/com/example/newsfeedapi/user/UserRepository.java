package com.example.newsfeedapi.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUsersByName(String name);

    Optional<User> findUsersById(String id);
    Optional<User> findUsersByGmail(String gmail);
    Optional<User> findUsersByGmailAndPassword(String gmail, String password);
    boolean existsUserByGmail(String gmail);

}
