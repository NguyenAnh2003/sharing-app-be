package com.example.socialapi.user;

import com.example.socialapi.user.dto.UserDTO;
import com.example.socialapi.user.requests.UpdateRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    private static final Logger logging = LoggerFactory.getLogger(UserController.class);

    @PutMapping(value = "/update/{userId}")
    public ResponseEntity<?> userUpdate(@PathVariable String userId, @RequestBody UpdateRequest req) {
        try {
            logging.info("updating user by userId");
            UserDTO user = userService.updateInfo(userId, req.getName(), req.getGender());
            if(user != null) return ResponseEntity.ok(user);
            else return new ResponseEntity("Cannot update b/c invalid info", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logging.error("Internal error cannot update user");
            return new ResponseEntity("Internal error cannot update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> fetchUserInfo(@PathVariable String userId) {
        try {
            logging.info("get user info");
            UserDTO user = userService.getUserInfo(userId); // init user dto
            if(user != null) return ResponseEntity.ok(user);
            else return new ResponseEntity("Cannot get user info", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logging.error("Internal error cannot get user info");
            return new ResponseEntity("Internal error cannot get user info", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // draft
    @GetMapping
    public ResponseEntity<List<UserDTO>> fetchAllUsers() {
        try {
            logging.info("get all users controller class");
            List<UserDTO> result = userService.getAllUsers();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logging.error("Internal error cannot get all users");
            return new ResponseEntity("Internal error cannot get all users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
