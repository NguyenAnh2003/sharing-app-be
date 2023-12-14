package com.example.socialapi.user;

import com.example.socialapi.user.dto.UserDTO;
import com.example.socialapi.user.requests.UpdateRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Tag(name = "User")
@SecurityRequirement(name = "bearerAuth")
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
