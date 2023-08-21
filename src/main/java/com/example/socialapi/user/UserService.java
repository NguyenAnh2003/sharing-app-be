package com.example.socialapi.user;

import com.example.socialapi.user.dto.UserDTO;
import com.example.socialapi.user.dto.UserDTOMapper;
import com.example.socialapi.user.requests.UpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        userRepository.save(user);
        return mapper.apply(user);
    }

    /* Convert timestamp */
    public UserDTO getUserInfo(String id) {
        User found = userRepository.findUsersById(id).orElseThrow();
        User user = new User(found.getId(),
                found.getName(),
                found.getGender(),
                found.getAvatarURL(),
                found.getTimestamp());
        return mapper.apply(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
