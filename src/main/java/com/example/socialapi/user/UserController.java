package com.example.socialapi.user;

import com.example.socialapi.user.dto.UserDTO;
import com.example.socialapi.user.requests.UpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping(value = "/{id}/update")
    public ResponseEntity<?> userUpdate(@PathVariable String id, @RequestBody UpdateRequest req) {
//        return ResponseEntity.ok(userService.updateInfo(id, req));
        /*
        Update user info
         */
        userService.updateInfo(id, req);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> fetchUserInfo(@PathVariable String id) {
      return ResponseEntity.ok(userService.getUserInfo(id));
    }

    // draft
    @GetMapping
    public List<UserDTO> fetchAllUsers() {
        return userService.getAllUsers();
    }
}
