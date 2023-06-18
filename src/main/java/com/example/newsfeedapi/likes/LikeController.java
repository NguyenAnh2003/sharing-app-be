package com.example.newsfeedapi.likes;

import com.example.newsfeedapi.likes.dto.LikeDTO;
import com.example.newsfeedapi.likes.request.LikeReq;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@AllArgsConstructor
public class LikeController {
    private final LikeService service;
    @PostMapping(value = "/create")
    public ResponseEntity<LikeDTO> likeAction(@RequestBody LikeReq req) {
        return ResponseEntity.ok(service.createLikeEntity(req));
    }
    @DeleteMapping(value = "/p/{postId}/u/{userId}/delete")
    public ResponseEntity<String> deleteLikeAct(@PathVariable String postId, @PathVariable String userId) {
        // https://stackoverflow.com/questions/34468834/user-likes-in-a-restful-api
        return ResponseEntity.ok(service.deleteLikeEntity(postId, userId));
    }
    @GetMapping(value = "/{postId}")
    public ResponseEntity<List<LikeDTO>> fetchAllByPostId(@PathVariable String postId) {
        return ResponseEntity.ok(service.getLikesByPostId(postId));
    }

}
