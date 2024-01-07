package com.example.socialapi.likes;

import com.example.socialapi.likes.dto.LikeDTO;
import com.example.socialapi.likes.dto.LikeDTOMapper;
import com.example.socialapi.likes.request.LikeReq;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeDTOMapper mapper;
    private static final Logger logging = LoggerFactory.getLogger(LikeService.class);

    public LikeDTO createLikeEntity(String userId, String postId) {
        try {
            logging.info("create like record service class");
            logging.debug("debugging create like record service class");
            Like l = new Like(new ObjectId(userId),
                    new ObjectId(postId));

            return mapper.apply(likeRepository.save(l));
        } catch (Exception e) {
            logging.error("cannot create like record service class");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public Boolean deleteLikeEntity(String postId, String userId) {
        try {
            logging.info("delete like record service class");
            logging.debug("debugging delete like record service class");
            likeRepository.deleteByPostIdAndUserId(new ObjectId(postId), new ObjectId(userId));
            return likeRepository.existsByPostIdAndUserId(new ObjectId(postId), new ObjectId(userId))
                    .orElseThrow();
        } catch (Exception e) {
            logging.error("cannot delete like record service class");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    /* read likes */
    public List<LikeDTO> getLikesByPostId(String postId) {
        try {
            logging.info("get all like records by postId");
            return likeRepository.findAllByPostId(new ObjectId(postId)).orElseThrow()
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logging.error("cannot get like records by postId service class");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }
}
