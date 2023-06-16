package com.example.newsfeedapi.post;

import com.example.newsfeedapi.post.dto.PostDTO;
import com.example.newsfeedapi.post.dto.PostDTOMapper;
import com.example.newsfeedapi.post.requests.CreatePostReq;
import com.example.newsfeedapi.post.requests.UpdatePostReq;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostDTOMapper mapper;
    /* create */
    public PostDTO createPostService(CreatePostReq req) {
        Post post = new Post(new ObjectId(req.getUserId()),
                new ObjectId(req.getCategoryId()),
                req.getTitle(),
                req.getDescription(),
                req.getImageURL(), LocalDateTime.now());
        return mapper.apply(repository.save(post));
    }
    /* update */
    public PostDTO updatePostService(String id, UpdatePostReq req) {
        Post post = repository.findById(id).orElseThrow();
        post.setUserId(new ObjectId(req.getUserId()));
        post.setCategoryId(new ObjectId(req.getCategoryId()));
        post.setTitle(req.getTitle());
        post.setDescription(req.getDescription());
        post.setImageURL(req.getImageURL());
        // set
        return mapper.apply(repository.save(post));
    }
    /* read */
    public List<PostDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(String id) {
        return mapper.apply(repository.findById(id).orElseThrow());
    }

    public List<PostDTO> getPostByUserId(String userId) {
        return repository.findPostByUserId(userId)
                .stream()
                .map(mapper).collect(Collectors.toList());
    }

    /* delete */
    public String deletePost(String id) {
        // https://stackoverflow.com/questions/55567213/how-to-check-for-success-failure-in-java-spring-database-queries
        Post p = repository.findById(id).orElseThrow();
        repository.delete(p);
        boolean existed = repository.existsById(id);
        return existed ? "Deleted" : "Failed";
    }

}
