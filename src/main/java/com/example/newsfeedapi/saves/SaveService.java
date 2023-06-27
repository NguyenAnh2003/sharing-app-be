package com.example.newsfeedapi.saves;

import com.example.newsfeedapi.post.PostRepository;
import com.example.newsfeedapi.post.dto.EmbeddedPostMapper;
import com.example.newsfeedapi.saves.dto.SaveDTO;
import com.example.newsfeedapi.saves.dto.SaveDTOMapper;
import com.example.newsfeedapi.saves.request.SaveReq;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
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
    public SaveDTO createEntityService(SaveReq req) {
        // embedding post object
        Save o = new Save(postMapper.apply(postRepository.findById(req.getPostId()).orElseThrow()),
                new ObjectId(req.getUserId()));
        return mapper.apply(repository.save(o));
    }
    public List<SaveDTO> getAllByPostId(ObjectId postId) {
        return repository.findAllByPostId(postId).orElseThrow()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
    public String deleteEntityService(ObjectId postId, ObjectId userId) {
//        Save o = repository.findByPostIdAndUserId(postId, userId)
//                .orElseThrow();
        repository.deleteByPostIdAndUserId(postId, userId);
        boolean exist = repository.existsByPostIdAndUserId(postId, userId)
                .orElseThrow();
        return exist ? "Failed" : "Deleted";
    }
}
