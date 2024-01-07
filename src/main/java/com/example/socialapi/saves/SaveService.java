package com.example.socialapi.saves;

import com.example.socialapi.post.PostRepository;
import com.example.socialapi.post.dto.EmbeddedPostMapper;
import com.example.socialapi.saves.dto.SaveDTO;
import com.example.socialapi.saves.dto.SaveDTOMapper;
import com.example.socialapi.saves.request.SaveReq;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaveService {

    private final SaveRepository repository;
    private final SaveDTOMapper mapper;
    private final PostRepository postRepository;
    private  final EmbeddedPostMapper postMapper;
    private static final Logger logging = LoggerFactory.getLogger(SaveService.class);

    public SaveDTO createEntityService(SaveReq req) {
        try {
            logging.debug("debugging create save record");
            Save o = new Save(postMapper.apply(postRepository.findById(req.getPostId()).orElseThrow()),
                    new ObjectId(req.getUserId()));
            return mapper.apply(repository.save(o));
        } catch (Exception e) {
            logging.error("Internal error creating save record");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public List<SaveDTO> getAllByPostId(String postId) {
        try {
            logging.info("get all post by postId");
            logging.debug("debugging get all posts by postId");
            return repository.findAllByPostId(new ObjectId(postId)).orElseThrow()
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logging.error("Internal error cannot get all saved posts by postId");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public List<SaveDTO> getAllByUserId(String userId) {
        try {
            logging.info("get all post by userId");
            logging.debug("debugging get all posts by userId");
            return repository.findAllByUserId(new ObjectId(userId)).orElseThrow()
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logging.error("Internal error cannot get all saved posts by userId");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public Boolean deleteEntityService(String postId, String userId) {
        try {
            logging.debug("debugging delete saved post");
            repository.deleteByPostIdAndUserId(new ObjectId(postId), new ObjectId(userId));
            return repository.existsByPostIdAndUserId(new ObjectId(postId), new ObjectId(userId))
                    .orElseThrow();
        } catch (Exception e) {
            logging.error("Internal error cannot delete saved post");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }
}
