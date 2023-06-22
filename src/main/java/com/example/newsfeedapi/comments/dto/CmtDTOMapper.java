package com.example.newsfeedapi.comments.dto;

import com.example.newsfeedapi.comments.Comment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CmtDTOMapper implements Function<Comment, CmtDTO> {

    @Override
    public CmtDTO apply(Comment comment) {
        return new CmtDTO(comment.getId(),
                comment.getContent(),
                comment.getUser(),
                comment.getPostId().toString());
    }
}
