package com.example.socialapi.saves;

import com.example.socialapi.saves.dto.SaveDTO;
import com.example.socialapi.saves.request.SaveReq;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
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
    /* create entity */
    @PostMapping(value = "/create")
    public ResponseEntity<SaveDTO> createEntity(@RequestBody SaveReq req) {
        return ResponseEntity.ok(service.createEntityService(req));
    }
    /* get saves by postId */
    @GetMapping(value = "/{postId}")
    public ResponseEntity<List<SaveDTO>> fetchAllByPostId(@PathVariable String postId) {
        return ResponseEntity.ok(service.getAllByPostId(new ObjectId(postId)));
    }
    /* delete entity by postId, userId */
    @DeleteMapping(value = "/p/{postId}/u/{userId}/delete")
    public ResponseEntity<String> deleteEntity(@PathVariable String postId, @PathVariable String userId) {
        return ResponseEntity.ok(service.deleteEntityService(new ObjectId(postId),new ObjectId(userId)));
    }
}
