package com.example.socialapi.post;

import com.example.socialapi.category.CategoryRepository;
import com.example.socialapi.category.dto.EmbeddedCategoryMapper;
import com.example.socialapi.follow.FollowService;
import com.example.socialapi.follow.dto.FollowDTO;
import com.example.socialapi.post.dto.PostDTO;
import com.example.socialapi.post.dto.PostDTOMapper;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final PostDTOMapper mapper;
    private final EmbeddedCategoryMapper categoryMapper;
    private final FollowService followService;
    private static final Logger logging = LoggerFactory.getLogger(PostService.class);

    /* create */
    public PostDTO createPostService(String userId, String categoryId,
                                     String title, String description,
                                     String imageURL) {
        try {
            logging.info("creating post in service");
            Post post = new Post(userId,
                    categoryMapper.apply(cateRepository.findById(categoryId).orElseThrow()),
                    title,
                    description,
                    imageURL, LocalDateTime.now());
            return mapper.apply(repository.save(post));
        } catch (Exception e) {
            logging.error("Internal error cannot create post");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    /* update */
    public PostDTO updatePostService(String id, String userId,
                                     String categoryId, String title,
                                     String description, String imageURL) {
        try {
            logging.info("update post with postId", id);
            Post post = repository.findById(id).orElseThrow();
            post.setCategory(categoryMapper.apply(cateRepository.findById(categoryId).orElseThrow()));
            post.setTitle(title);
            post.setDescription(description);
            post.setImageURL(imageURL);
            return mapper.apply(repository.save(post));
        } catch (Exception e) {
            logging.error("Internal error cannot update post");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    /* read */
    public List<PostDTO> getAll(String userId) {
        try {
            logging.info("get all posts by userId and followingUserId");
            List<Post> listOfPosts = new ArrayList<Post>();
            listOfPosts.clear();
            // define posts array for result fetching
            List<Post> posts = repository.findAllByUserId(userId).orElseThrow();
//            if(posts.isEmpty()) return Collections.emptyList();
            listOfPosts.addAll(posts); // add all posts fetched by userId
            List<Post> followingUsersPosts = new ArrayList<Post>(); // following users posts
            List<FollowDTO> followingUsers = followService.getFollowingUsers(userId); // get following users by userId
//            if(followingUsers.isEmpty()) return Collections.emptyList();
            followingUsers.forEach(
                    follow -> {
                        followingUsersPosts.addAll(repository.findAllByUserId(String.valueOf(follow.getFollowingId())).orElseThrow());
                        listOfPosts.addAll(followingUsersPosts); // add to result
//                        followingUsersPosts.clear(); // remove init array after adding
                    }
            );
            return listOfPosts.isEmpty() ? Collections.emptyList() : listOfPosts.stream().map(mapper).collect(Collectors.toList());
        } catch (Exception e) {
            logging.error("Internal error cannot get all posts");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public PostDTO getPostById(String id) {
        try {
            logging.info("get single post by postId");
            return mapper.apply(repository.findById(id).orElseThrow());
        } catch (Exception e) {
            logging.error("Internal error cannot get post by postId");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public List<PostDTO> getPostsByUserId(String userId) {
        try {
            logging.info("get all posts by userId");
            return repository.findAllByUserId(userId).orElseThrow()
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logging.error("Internal error cannot get posts by userId");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    /* delete */
    public Boolean deletePost(String id) {
        try {
            logging.info("deleting post", id);
            Post p = repository.findById(id).orElseThrow();
            repository.delete(p);
            boolean existed = repository.existsById(id);
            return existed;
        } catch (Exception e) {
            logging.error("Internal error cannot delete post");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

}
