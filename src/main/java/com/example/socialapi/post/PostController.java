package com.example.socialapi.post;

import com.example.socialapi.post.dto.PostDTO;
import com.example.socialapi.post.requests.CreatePostReq;
import com.example.socialapi.post.requests.UpdatePostReq;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/p")
@AllArgsConstructor
@Tag(name = "Post")
@SecurityRequirement(name = "bearerAuth")
public class PostController {

    private final PostService service;
    private static final Logger logging = LoggerFactory.getLogger(PostController.class);

    @PostMapping(value = "/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody CreatePostReq req) {
        try {
            logging.info("create post method from post controller");
            PostDTO createdPost = service.createPostService(req.getUserId(),
                    req.getCategoryId(), req.getTitle(), req.getDescription(),
                    req.getImageURL());
            if(createdPost != null) return ResponseEntity.ok(createdPost);
            else return new ResponseEntity("There may be invalid info you provided", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logging.error("Cannot create post because error", e.getCause());
            return new ResponseEntity("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable String postId, @RequestBody UpdatePostReq req) {
        try {
            logging.info("update post from post controller");
            PostDTO updatedPost = service.updatePostService(postId, req.getUserId(),
                    req.getCategoryId(), req.getTitle(), req.getDescription(),
                    req.getImageURL());
            if(updatedPost != null) return ResponseEntity.ok(updatedPost);
            else return new ResponseEntity("Invalid info provided", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logging.error("Internal error");
            return new ResponseEntity("Internal error cannot update post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Read integrate with category */
    @GetMapping(value = "/get-all/{userId}")
    public ResponseEntity<List<PostDTO>> fetchPosts(@PathVariable String userId) {
        try {
            logging.info("get all posts controller");
            List<PostDTO> listOfPost = service.getAll(userId);
            return ResponseEntity.ok(listOfPost);
        } catch (Exception e) {
            logging.error("Internal error in get all post", e.getCause());
            return new ResponseEntity("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* getById */
    @GetMapping(value = "/{postId}")
    public ResponseEntity<PostDTO> fetchSinglePost(@PathVariable String postId) {
        try {
            logging.info("get single post in post controller");
            PostDTO post = service.getPostById(postId);
            if(post != null) return ResponseEntity.ok(post);
            else return new ResponseEntity("Cannot find post", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logging.error("Internal in find single post with postId", postId);
            return new ResponseEntity("Cannot find post because of internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* userId */
    @GetMapping(value = "/u/{userId}")
    public ResponseEntity<List<PostDTO>> fetchUserPosts(@PathVariable String userId) {
        try {
            logging.info("getPostsByuserId", userId);
            List<PostDTO> userPosts = service.getPostsByUserId(userId);
            return ResponseEntity.ok(userPosts);
        } catch (Exception e) {
            logging.error("Internal error in getPostByUserId", e.getCause());
            return new ResponseEntity("Cannot get posts by userId b/c Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Delete */
    @DeleteMapping(value = "/{postId}/delete")
    public ResponseEntity<?> deletePost(@PathVariable String postId) {
        try {
            logging.info("deleting post with postId", postId);
            Boolean exist = service.deletePost(postId);
            if(!exist) return new ResponseEntity("Delete successfully", HttpStatus.NO_CONTENT);
            else return new ResponseEntity("Cannot delete post", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logging.error("Internal error cannot delete post", e.getCause());
            return new ResponseEntity("Internal error cannot delete post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
