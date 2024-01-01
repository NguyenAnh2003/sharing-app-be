package com.example.socialapi.follow;

import com.example.socialapi.follow.dto.FollowDTO;
import com.example.socialapi.follow.request.FollowRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.http.protocol.HTTP;
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
    @PostMapping(value = "/create")
    public ResponseEntity<?> followUser(@RequestBody FollowRequest request) {
        /**
         * userId
         * followingId -> following user
         * return DTO object
         */
        try {
            FollowDTO followingUser = followService.startFollowUser(request.getUserId(), request.getFollowingUserId());
            if(followingUser != null) return ResponseEntity.ok(followingUser);
            else return new ResponseEntity("Cannot follow this person", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get-followers/{userId}")
    public ResponseEntity<List<?>> getFollowersByUserId(@PathVariable String userId) {
        try {
            List<FollowDTO> followers = followService.getFollowingUsers(userId);
            if(followers != null) return ResponseEntity.ok(followers);
            else return new ResponseEntity("Cannot get followers", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{followingUserId}/{userId}/delete")
    public ResponseEntity<?> deleteFollowingUser(@PathVariable String followingUserId,
                                                 @PathVariable String userId) {
        try {
            Boolean result = followService.deleteFollowingUser(userId, followingUserId);
            if(result == true) return new ResponseEntity("Cannot unfollow this person", HttpStatus.BAD_REQUEST);
            else return new ResponseEntity("Delete successfullly", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
