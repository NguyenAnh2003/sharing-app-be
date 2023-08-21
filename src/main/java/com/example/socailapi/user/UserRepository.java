package com.example.socailapi.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<List<User>> findUsersByName(String name);
    Optional<User> findUsersById(String id);
    Optional<User> findUsersByGmail(String gmail);

}
