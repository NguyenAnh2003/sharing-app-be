package com.example.socialapi.saves;

import com.example.socialapi.saves.dto.SaveDTO;
import com.example.socialapi.saves.request.SaveReq;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saves")
@AllArgsConstructor
@Tag(name = "Save")
@SecurityRequirement(name = "bearerAuth")
public class SaveController {
    private final SaveService service;
    private static final Logger logging = LoggerFactory.getLogger(SaveController.class);

    /* create entity */
    @PostMapping(value = "/create")
    public ResponseEntity<SaveDTO> createEntity(@RequestBody SaveReq req) {
        try {
            logging.debug("create save entity");
            return ResponseEntity.ok(service.createEntityService(req.getPostId(), req.getUserId()));
        } catch (Exception e) {
            logging.error("Internal error cannot save post");
            return new ResponseEntity("Internal error cannot save post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* get saves by postId */
    @GetMapping(value = "p/{postId}")
    public ResponseEntity<List<SaveDTO>> fetchAllByPostId(@PathVariable String postId) {
        try {
            logging.debug("get all saved posts by postId");
            return ResponseEntity.ok(service.getAllByPostId(postId));
        } catch (Exception e) {
            logging.error("Internal error cannot get saved posts by postId");
            return new ResponseEntity("Internal error cannot get saved posts", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* get all by userId */
    @GetMapping(value = "u/{userId}")
    public ResponseEntity<List<SaveDTO>> fetchAllByUserId(@PathVariable String userId) {
        try {
            logging.debug("get all saved posts by userId");
            return ResponseEntity.ok(service.getAllByUserId(userId));
        } catch (Exception e) {
            logging.error("Internal error cannot get saved posts by userId");
            return new ResponseEntity("Internal error cannot get saved posts", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* delete entity by postId, userId */
    @DeleteMapping(value = "/p/{postId}/u/{userId}/delete")
    public ResponseEntity<?> deleteEntity(@PathVariable String postId, @PathVariable String userId) {
        try {
            logging.debug("deleting saved post");
            Boolean exist = service.deleteEntityService(postId,userId);
            return new ResponseEntity("Delete successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logging.error("Internal error cannot delete saved post");
            return new ResponseEntity("Internal error cannot delete saved post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
