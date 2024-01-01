package com.example.socialapi.comments;

import com.example.socialapi.comments.dto.CommnetDTO;
import com.example.socialapi.comments.request.CreateCommentReq;
import com.example.socialapi.comments.request.UpdateCommentReq;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
@Tag(name = "Comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
    private final CommentService service;
    @PostMapping(value = "/create")
    public ResponseEntity<CommnetDTO> createCmtEntity(@RequestBody CreateCommentReq req) {
        return ResponseEntity.ok(service.createCmtEntityService(req));
    }
    @PutMapping(value = "/{id}/update")
    public ResponseEntity<CommnetDTO> updateCmtEntity(@PathVariable String id, @RequestBody UpdateCommentReq req) {
        return ResponseEntity.ok(service.updateCmtEntityService(id, req));
    }
    @GetMapping(value = "/p/{postId}")
    public ResponseEntity<List<CommnetDTO>> fetchAllByPostId(@PathVariable String postId) {
        return ResponseEntity.ok(service.getAllByPostId(new ObjectId(postId)));
    }
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<String> deleteEntity(@PathVariable String id) {
        return ResponseEntity.ok(service.deleteCmt(id));
    }
}
