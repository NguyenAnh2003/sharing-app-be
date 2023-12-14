package com.example.socialapi.post;

import com.example.socialapi.post.dto.PostDTO;
import com.example.socialapi.post.requests.CreatePostReq;
import com.example.socialapi.post.requests.UpdatePostReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/p")
@AllArgsConstructor
@Tag(name = "Post")
public class PostController {
    private final PostService service;
    @PostMapping(value = "/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody CreatePostReq req) {
        return ResponseEntity.ok(service.createPostService(req));
    }

    @PutMapping(value = "/{id}/update")
    public ResponseEntity<PostDTO> updatePost(@PathVariable String id, @RequestBody UpdatePostReq req) {
        return ResponseEntity.ok(service.updatePostService(id, req));
    }

    /* Read integrate with category */
    @GetMapping()
    public ResponseEntity<List<PostDTO>> fetchPosts() {
        return ResponseEntity.ok(service.getAll());
    }

    /* getById */
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> fetchSinglePost(@PathVariable String id) {
        return ResponseEntity.ok(service.getPostById(id));
    }

    /* userId */
    @GetMapping(value = "/u/{id}")
    public ResponseEntity<List<PostDTO>> fetchUserPosts(@PathVariable String id) {
        return ResponseEntity.ok(service.getPostsByUserId(id));
    }

    /* Delete */
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        return ResponseEntity.ok(service.deletePost(id));
    }

}
