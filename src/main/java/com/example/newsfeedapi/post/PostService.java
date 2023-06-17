package com.example.newsfeedapi.post;

import com.example.newsfeedapi.category.CateRepository;
import com.example.newsfeedapi.category.dto.EmbeddedCategoryMapper;
import com.example.newsfeedapi.post.dto.PostDTO;
import com.example.newsfeedapi.post.dto.PostDTOMapper;
import com.example.newsfeedapi.post.requests.CreatePostReq;
import com.example.newsfeedapi.post.requests.UpdatePostReq;
import com.example.newsfeedapi.user.UserRepository;
import com.example.newsfeedapi.user.dto.EmbeddedUserMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final CateRepository cateRepository;
    private final UserRepository userRepository;
    private final PostDTOMapper mapper;
    private final EmbeddedUserMapper userMapper;
    private final EmbeddedCategoryMapper categoryMapper;
    /* create */
    public PostDTO createPostService(CreatePostReq req) {
        Post post = new Post(userMapper.apply(userRepository.findUsersById(req.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found"))),
                categoryMapper.apply(cateRepository.findById(req.getCategoryId()).orElseThrow()),
                req.getTitle(),
                req.getDescription(),
                req.getImageURL(), LocalDateTime.now());
        return mapper.apply(repository.save(post));
    }
    /* update */
    public PostDTO updatePostService(String id, UpdatePostReq req) {
        Post post = repository.findById(id).orElseThrow();
        post.setUser(userMapper.apply(userRepository.findUsersById(req.getUserId()).orElseThrow()));
        post.setCategory(categoryMapper.apply(cateRepository.findById(req.getCategoryId()).orElseThrow()));
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

    public List<PostDTO> getPostsByUserId(String userId) {
        return repository.findAllByUserId(userId).orElseThrow()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
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
