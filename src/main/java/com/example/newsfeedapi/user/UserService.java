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

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper mapper;

    public UserDTO updateInfo(String id, UpdateRequest req) {
        User user = userRepository.findUsersById(id).orElseThrow();
        user.setName(req.getName());
        user.setGender(req.getGender());
        user.setAvatarURL(req.getAvatarURL());
        userRepository.save(user);
        return mapper.apply(user);
    }

    /* Convert timestamp */
    public UserDTO getUserInfo(String id) {
        Optional<User> found = userRepository.findUsersById(id);
        User user = new User(found.get().getId(),
                found.get().getName(),
                found.get().getGender(),
                found.get().getAvatarURL(),
                found.get().getTimestamp());
        return mapper.apply(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper)// extract from model -> DTO
                .collect(Collectors.toList());
    }
}
