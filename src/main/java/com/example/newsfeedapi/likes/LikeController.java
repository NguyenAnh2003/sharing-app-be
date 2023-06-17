package com.example.newsfeedapi.likes;

import com.example.newsfeedapi.likes.dto.LikeDTO;
import com.example.newsfeedapi.likes.request.LikeReq;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/l")
@AllArgsConstructor
public class LikeController {
    private final LikeService service;
    @PostMapping(value = "/like")
    public ResponseEntity<LikeDTO> likeAction(@RequestBody LikeReq req) {
        return ResponseEntity.ok(service.createLikeEntity(req));
    }


}
