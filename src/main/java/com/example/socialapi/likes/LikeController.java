package com.example.socialapi.likes;

import com.example.socialapi.likes.dto.LikeDTO;
import com.example.socialapi.likes.request.LikeReq;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@AllArgsConstructor
@Tag(name = "Like")
@SecurityRequirement(name = "bearerAuth")
public class LikeController {

    private final LikeService service;
    private static final Logger logging = LoggerFactory.getLogger(LikeController.class);

    @PostMapping(value = "/create")
    public ResponseEntity<LikeDTO> likeAction(@RequestBody LikeReq req) {
        try {
            logging.info("create like record");
            logging.debug("debugging create like record");
            return ResponseEntity.ok(service.createLikeEntity(req.getUserId(), req.getPostId()));
        } catch (Exception e) {
            logging.error("Internal error cannot create like record");
            return new ResponseEntity("Internal error cannot like this post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/p/{postId}/u/{userId}/delete")
    public ResponseEntity<?> deleteLikeAct(@PathVariable String postId, @PathVariable String userId) {
        try {
            logging.debug("debugging delete like record");
            Boolean exist = service.deleteLikeEntity(postId,userId);
            if(!exist) return new ResponseEntity("Unliked post", HttpStatus.NO_CONTENT);
            else return new ResponseEntity("Internal error cannot delete like record", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logging.error("internal error cannot delete like record");
            return new ResponseEntity("Internal error cannot delete like record", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<List<LikeDTO>> fetchAllByPostId(@PathVariable String postId) {
        try {
            logging.debug("debugging get all like record");
            return ResponseEntity.ok(service.getLikesByPostId(postId));
        } catch (Exception e) {
            logging.error("Internal error cannot get all like records");
            return new ResponseEntity("Internal error cannot get all like records", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
