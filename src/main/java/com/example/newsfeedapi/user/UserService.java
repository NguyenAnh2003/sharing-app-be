package com.example.newsfeedapi.user;

import com.example.newsfeedapi.user.dto.UserDTO;
import com.example.newsfeedapi.user.dto.UserDTOMapper;
import com.example.newsfeedapi.user.requests.UpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
