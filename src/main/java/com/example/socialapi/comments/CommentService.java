package com.example.socialapi.comments;

import com.example.socialapi.comments.dto.CommentDTO;
import com.example.socialapi.comments.dto.CommentDTOMapper;
import com.example.socialapi.comments.request.CreateCommentReq;
import com.example.socialapi.comments.request.UpdateCommentReq;
import com.example.socialapi.user.UserRepository;
import com.example.socialapi.user.dto.EmbeddedUserMapper;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final CommentDTOMapper mapper;
    private final EmbeddedUserMapper userMapper;
    private static final Logger logging = LoggerFactory.getLogger(CommentService.class);

    public CommentDTO createCommentService(String userId, String postId,
                                                 String content) {
        try {
            logging.debug("creating comment entity service class");
            Comment object = new Comment(userMapper.apply(userRepository.findUserById(new ObjectId(userId)).orElseThrow()),
                    new ObjectId(postId),
                    content,
                    LocalDateTime.now());
            return mapper.apply(repository.save(object));
        } catch (Exception e) {
            logging.error("cannot create comment", e.getMessage());
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public CommentDTO updateCommentService(String id, String userId, String postId, String content) {
        /**
         * find Comment by comment id (click on comment and focus on that comment)
         * then update the content also set timestamp
         * Alternative method: findByUserIdAndPostId
         */
        try {
            logging.debug("updating comment content");
            Comment object = repository.findById(id).orElseThrow();
//        object.setUser(userMapper.apply(userRepository.findUserById(new ObjectId(userId)).orElseThrow()));
//        o.setPostId(new ObjectId(req.getPostId()));
            object.setContent(content);
            object.setTimestamp(LocalDateTime.now()); // setup updated time
            return mapper.apply(repository.save(object));
        } catch (Exception e) {
            logging.error("cannot update comment content");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public List<CommentDTO> getAllCommentsService(String postId) {
        try {
            logging.debug("getting all comments by postId", postId);
            return repository.findAllByPostId(new ObjectId(postId)).orElseThrow()
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logging.error("cannot get all comments by postId", postId);
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public Boolean deleteCommentService(String id) {
        /**
         * simply finding commentId in Comment collection and remove
         * dont have to get postId to find comment
         */
        try {
            logging.debug("deleting comment service class");
            Comment object = repository.findById(id).orElseThrow();
            repository.delete(object);
            boolean exist = repository.existsById(id); // checking existed comment
            return exist;
        } catch (Exception e) {
            logging.error("cannot delete comment service class");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

}
