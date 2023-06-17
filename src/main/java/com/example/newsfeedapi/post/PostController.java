package com.example.newsfeedapi.post;

import com.example.newsfeedapi.post.dto.PostDTO;
import com.example.newsfeedapi.post.requests.CreatePostReq;
import com.example.newsfeedapi.post.requests.UpdatePostReq;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/p")
@AllArgsConstructor
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
    public String deletePost(@PathVariable String id) {
        return service.deletePost(id);
    }

}
