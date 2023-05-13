package com.example.newsfeedapi.user;

import com.example.newsfeedapi.user.dto.UserDTO;
import com.example.newsfeedapi.user.dto.UserDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper mapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper)// extract from model -> DTO
                .collect(Collectors.toList());
    }
}
