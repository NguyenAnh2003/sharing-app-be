package com.example.socailapi.comments;

import com.example.socailapi.comments.dto.CmtDTO;
import com.example.socailapi.comments.dto.CmtDTOMapper;
import com.example.socailapi.comments.request.CreateCmtReq;
import com.example.socailapi.comments.request.UpdateCmtReq;
import com.example.socailapi.user.UserRepository;
import com.example.socailapi.user.dto.EmbeddedUserMapper;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CmtService {
    private final CmtRepository repository;
    private final UserRepository userRepository;
    private final CmtDTOMapper mapper;
    private final EmbeddedUserMapper userMapper;

    public CmtDTO createCmtEntityService(CreateCmtReq req) {
        Comment o = new Comment(userMapper.apply(userRepository.findUsersById(req.getUserId()).orElseThrow()),
                new ObjectId(req.getPostId()),
                req.getContent(),
                LocalDateTime.now());
        return mapper.apply(repository.save(o));
    }
    public CmtDTO updateCmtEntityService(String id, UpdateCmtReq req) {
        Comment o = repository.findById(id).orElseThrow();
        o.setUser(userMapper.apply(userRepository.findUsersById(req.getUserId()).orElseThrow()));
        o.setPostId(new ObjectId(req.getPostId()));
        o.setContent(req.getContent());
        return mapper.apply(repository.save(o));
    }
    public List<CmtDTO> getAllByPostId(ObjectId postId) {
        return repository.findAllByPostId(postId).orElseThrow()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
    public String deleteCmt(String id) {
        Comment o = repository.findById(id).orElseThrow();
        repository.delete(o);
        boolean exist = repository.existsById(id);
        return exist ? "Failed" : "Deleted";
    }

}
