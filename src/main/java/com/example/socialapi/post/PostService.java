package com.example.socialapi.post;

import com.example.socialapi.category.CategoryRepository;
import com.example.socialapi.category.dto.EmbeddedCategoryMapper;
import com.example.socialapi.follow.Follow;
import com.example.socialapi.post.dto.PostDTO;
import com.example.socialapi.post.dto.PostDTOMapper;
import com.example.socialapi.post.requests.CreatePostReq;
import com.example.socialapi.post.requests.UpdatePostReq;
import com.example.socialapi.user.UserRepository;
import com.example.socialapi.user.dto.EmbeddedUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final CategoryRepository cateRepository;
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
//        post.setUser(userMapper.apply(userRepository.findUsersById(req.getUserId()).orElseThrow()));
        post.setCategory(categoryMapper.apply(cateRepository.findById(req.getCategoryId()).orElseThrow()));
        post.setTitle(req.getTitle());
        post.setDescription(req.getDescription());
        post.setImageURL(req.getImageURL());
        // set
        return mapper.apply(repository.save(post));
    }
    /* read */
    public List<PostDTO> getAll(String userId) {
        /**
         * get All post for home page
         * by using userId to get user posts
         * userId -> followerId
         * join followerId to postId
         * if not return Array[]
         */
        List<Post> listOfPosts = new ArrayList<Post>();
        // define posts array for result fetching
        List<Post> posts = repository.findAllByUserId(userId).orElseThrow();
        if(posts.isEmpty()) return Collections.emptyList();
        else listOfPosts.addAll(posts); // add all posts fetched by userId
        // fetching followingId by userId
        List<Post> followingUsersPosts = new ArrayList<Post>();
        List<Follow> followingUsers = new ArrayList<>(); // get following users by userId
        if(followingUsers.isEmpty()) return Collections.emptyList();
        else {followingUsers.forEach(
                follow -> {
                    followingUsersPosts.addAll(repository.findAllByUserId(String.valueOf(follow.getFollowingId())).orElseThrow());
                    listOfPosts.addAll(followingUsersPosts);
                }
        );};
        return listOfPosts.isEmpty() ? Collections.emptyList() : listOfPosts.stream().map(mapper).collect(Collectors.toList());
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
        return existed ? "Failed" : "Deleted";
    }

}
