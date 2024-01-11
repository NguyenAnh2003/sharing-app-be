package com.example.socialapi.comments.dto;

import com.example.socialapi.comments.Comment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CommentDTOMapper implements Function<Comment, CommentDTO> {

    @Override
    public CommentDTO apply(Comment comment) {
        return new CommentDTO(comment.getId(),
                comment.getContent(),
                comment.getUserId(),
                comment.getPostId().toString(),
                comment.getTimestamp());
    }
}
