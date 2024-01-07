package com.example.socialapi.follow;

import com.example.socialapi.follow.dto.FollowDTO;
import com.example.socialapi.follow.request.FollowRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/follow")
@Tag(name = "Follow")
@SecurityRequirement(name = "bearerAuth")
public class FollowController {

    private final FollowService followService;
    private static final Logger logging = LoggerFactory.getLogger(FollowController.class);

    @PostMapping(value = "/create")
    public ResponseEntity<?> followUser(@RequestBody FollowRequest request) {
        /* followerId: userId, followingId: followingUserId */
        try {
            logging.info("creating follow entity with userId and followingUserId",
                    request.getUserId(), request.getFollowingUserId());
            FollowDTO followingUser = followService.startFollowUser(request.getUserId(), request.getFollowingUserId());
            if(followingUser != null) return ResponseEntity.ok(followingUser);
            else return new ResponseEntity("Cannot follow this person", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logging.error("Interal error in following user");
            return new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get-followers/{userId}")
    public ResponseEntity<List<FollowDTO>> getFollowersByUserId(@PathVariable String userId) {
        try {
            logging.info("get followers by userId");
            List<FollowDTO> followers = followService.getFollowingUsers(userId);
            if(followers != null) return ResponseEntity.ok(followers);
            else return new ResponseEntity("Cannot get followers", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logging.error("Internal error in get followers w userId");
            return new ResponseEntity("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{followingUserId}/{userId}/delete")
    public ResponseEntity<?> deleteFollowingUser(@PathVariable String followingUserId,
                                                 @PathVariable String userId) {
        try {
            logging.info("delete follow entity with userId and followingUserId");
            Boolean exist = followService.deleteFollowingUser(userId, followingUserId);
            if(exist) return new ResponseEntity<>("Cannot unfollow this person", HttpStatus.BAD_REQUEST);
            else return new ResponseEntity<>("Delete successfullly", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logging.error("Internal error in deleting follow entity");
            return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
