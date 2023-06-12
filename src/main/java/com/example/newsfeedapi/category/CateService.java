package com.example.newsfeedapi.category;

import com.example.newsfeedapi.category.dto.CateDTO;
import com.example.newsfeedapi.category.dto.CateDTOMapper;
import com.example.newsfeedapi.category.request.CreateCateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CateService {
    private final CateRepository repository;
    private final CateDTOMapper mapper;

    public CateDTO createCateService(CreateCateRequest req) {
        Category cate = new Category(req.getCategory(), LocalDateTime.now());
        repository.save(cate);
        return mapper.apply(cate);
    }

    public List<CateDTO> getAllCategory() {
        return repository.findAll()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
