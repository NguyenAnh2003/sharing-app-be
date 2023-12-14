package com.example.socialapi.category;

import com.example.socialapi.category.dto.CategoryDTO;
import com.example.socialapi.category.dto.CategoryDTOMapper;
import com.example.socialapi.category.request.CreateCategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryDTOMapper mapper;

    public CategoryDTO createCateService(CreateCategoryRequest req) {
        Category cate = new Category(req.getCategory(), LocalDateTime.now());
        repository.save(cate);
        return mapper.apply(cate);
    }

    public List<CategoryDTO> getAllCategory() {
        return repository.findAll()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
