package com.example.newsfeedapi.likes;

import com.example.newsfeedapi.likes.dto.LikeDTO;
import com.example.newsfeedapi.likes.dto.LikeDTOMapper;
import com.example.newsfeedapi.likes.request.LikeReq;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final LikeDTOMapper mapper;

    public LikeDTO createLikeEntity(LikeReq req) {
        Like l = new Like(new ObjectId(req.getUserId()),
                new ObjectId(req.getPostId()));

        return mapper.apply(likeRepository.save(l));
    }
}
