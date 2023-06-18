package com.example.newsfeedapi.saves;

import com.example.newsfeedapi.post.PostRepository;
import com.example.newsfeedapi.post.dto.PostDTOMapper;
import com.example.newsfeedapi.saves.dto.SaveDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaveService {
    private final SaveRepository repository;
    private final SaveDTOMapper mapper;
    private final PostRepository postRepository;
    private  final PostDTOMapper postMapper;
}
