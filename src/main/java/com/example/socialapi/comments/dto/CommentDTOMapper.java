package com.example.socialapi.comments.dto;

import com.example.socialapi.comments.Comment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CommentDTOMapper implements Function<Comment, CommnetDTO> {

    @Override
    public CommnetDTO apply(Comment comment) {
        return new CommnetDTO(comment.getId(),
                comment.getContent(),
                comment.getUser(),
                comment.getPostId().toString());
    }
}
