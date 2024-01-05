package com.example.socialapi.comments;

import com.example.socialapi.comments.dto.CommentDTO;
import com.example.socialapi.comments.request.CreateCommentReq;
import com.example.socialapi.comments.request.UpdateCommentReq;
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
@RequestMapping("/api/comment")
@AllArgsConstructor
@Tag(name = "Comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {

    private final CommentService service;
    private static final Logger logging = LoggerFactory.getLogger(CommentController.class);

    @PostMapping(value = "/create")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CreateCommentReq req) {
        /**
         * create comment for a post with Request body
         * @param: userId, postId
         * @params: content
         * return with all above attr and timestamp
         */
        try {
            logging.debug("creating comment entity");
            CommentDTO comment = service.createCommentService(req.getUserId(), req.getPostId(), req.getContent());
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            logging.error("Internal error - Cannot create comment at controller");
            return new ResponseEntity("Internal error cannot comment on this post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable String commentId, @RequestBody UpdateCommentReq req) {
        try {
            logging.debug("updating comment");
            return ResponseEntity.ok(service.updateCommentService(commentId, req.getUserId(), req.getPostId(), req.getContent()));
        } catch (Exception e) {
            logging.error("cannot update comment controller class");
            return new ResponseEntity("Internal error cannot update comment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/p/{postId}")
    public ResponseEntity<List<CommentDTO>> fetchAllComments(@PathVariable String postId) {
        /** get all comments by postId */
        try {
            logging.debug("getting all comments on post", postId);
            return ResponseEntity.ok(service.getAllCommentsService(postId));
        } catch (Exception e) {
            logging.error("cannot get all comments on post");
            return new ResponseEntity("Internal error cannot get all comments", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<?> removeCommentOnPost(@PathVariable String id) {
        try {
            logging.debug("deleting comment controller class");
            Boolean exist = service.deleteCommentService(id);
            /* return 204 no content */
            if(exist) return new ResponseEntity("delete sucessfully", HttpStatus.NO_CONTENT);
            else return new ResponseEntity("Delete failed cannot find comment record", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logging.error("cannot delete comment controller class");
            return new ResponseEntity("Cannot delete comment from this post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
