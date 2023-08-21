package com.example.socailapi.comments;

import com.example.socailapi.comments.dto.CmtDTO;
import com.example.socailapi.comments.request.CreateCmtReq;
import com.example.socailapi.comments.request.UpdateCmtReq;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CmtController {
    private final CmtService service;
    @PostMapping(value = "/create")
    public ResponseEntity<CmtDTO> createCmtEntity(@RequestBody CreateCmtReq req) {
        return ResponseEntity.ok(service.createCmtEntityService(req));
    }
    @PutMapping(value = "/{id}/update")
    public ResponseEntity<CmtDTO> updateCmtEntity(@PathVariable String id, @RequestBody UpdateCmtReq req) {
        return ResponseEntity.ok(service.updateCmtEntityService(id, req));
    }
    @GetMapping(value = "/p/{postId}")
    public ResponseEntity<List<CmtDTO>> fetchAllByPostId(@PathVariable String postId) {
        return ResponseEntity.ok(service.getAllByPostId(new ObjectId(postId)));
    }
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<String> deleteEntity(@PathVariable String id) {
        return ResponseEntity.ok(service.deleteCmt(id));
    }
}
