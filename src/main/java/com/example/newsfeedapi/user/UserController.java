package com.example.newsfeedapi.user;

import com.example.newsfeedapi.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<UserDTO> fetchAllUsers() {
        return userService.getAllUsers();
    }
}
