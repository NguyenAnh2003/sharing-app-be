package com.example.socialapi.user;

import com.example.socialapi.post.Post;
import com.example.socialapi.post.PostRepository;
import com.example.socialapi.user.dto.UserDTO;
import com.example.socialapi.user.dto.UserDTOMapper;
import com.example.socialapi.user.requests.UpdateRequest;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper mapper;
    private final PostRepository postRepository; // post repository
    private static final Logger logging = LoggerFactory.getLogger(UserService.class);

    public UserDTO updateInfo(String userId, String name) {
        try {
            logging.info("update user in service class w userId");
            User user = userRepository.findUserById(userId).orElseThrow();
            user.setName(name);
            User savedUser = userRepository.save(user);
            return mapper.apply(savedUser);
        } catch (Exception e) {
            logging.error("Internal error", e.getMessage());
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public UserDTO getUserInfo(String id) {
        /* get by userId */
        try {
            logging.info("get user by userId service class");
            User found = userRepository.findUserById(id).orElseThrow();
            User user = new User(found.getId(), found.getName(), found.getAvatarURL(), found.getTimestamp());
            return mapper.apply(user);
        } catch (Exception e) {
            logging.error("Internal error", e.getMessage());
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public List<UserDTO> getAllUsers() {
        try {
            logging.info("get all users in service class");
            return userRepository.findAll().stream().map(mapper).collect(Collectors.toList());
        } catch (Exception e) {
            logging.error("Internal error cannot get all users");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }
}
