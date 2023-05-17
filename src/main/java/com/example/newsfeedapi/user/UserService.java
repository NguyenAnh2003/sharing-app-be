package com.example.newsfeedapi.user;

import com.example.newsfeedapi.user.dto.UserDTO;
import com.example.newsfeedapi.user.dto.UserDTOMapper;
import com.example.newsfeedapi.user.requests.LoginRequest;
import com.example.newsfeedapi.user.requests.RegisterRequest;
import com.example.newsfeedapi.user.requests.UpdateRequest;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper mapper;
    private final MongoTemplate mongoTemplate;


    public UserDTO createUser(RegisterRequest u) {
        // validate exist gmail
        String gmail = String.valueOf(userRepository.findUsersByGmail(u.getGmail()));
        if(userRepository.existsUserByGmail(gmail)) {
            throw new DuplicateRequestException("Gmail already exists");
        }
        User user = new User(u.getName(),
                u.getGmail(),
                u.getPassword(),
                u.getGender(),
                u.getAvatarURL());

        return mapper.apply(userRepository.insert(user));
    }

    public UserDTO checkLogin(LoginRequest u) {
        Optional<User> userFound = userRepository.findUsersByGmailAndPassword(u.getGmail(), u.getPassword());
        if(userFound == null) {
            throw new RuntimeException("Invalid");
        }
        User user = new User(userFound.get().getId(),
                userFound.get().getName(),
                userFound.get().getGender(),
                userFound.get().getAvatarURL());
        return mapper.apply(user);
    }

    public UserDTO updateInfo(String id, UpdateRequest u) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        Update update = new Update()
                .set("name", u.getName())
                .set("gender", u.getGender())
                .set("avatarURL", u.getAvatarURL());
        User user = mongoTemplate.findAndModify(query, update, User.class);
        return mapper.apply(user);
    }

    public UserDTO getUserInfo(String id) {
        Optional<User> found = userRepository.findUsersById(id);
        User user = new User(found.get().getId(),
                found.get().getName(),
                found.get().getGender(),
                found.get().getAvatarURL());
        return mapper.apply(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper)// extract from model -> DTO
                .collect(Collectors.toList());
    }
}
