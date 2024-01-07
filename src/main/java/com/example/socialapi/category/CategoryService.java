package com.example.socialapi.category;

import com.example.socialapi.category.dto.CategoryDTO;
import com.example.socialapi.category.dto.CategoryDTOMapper;
import com.example.socialapi.category.request.CreateCategoryRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryDTOMapper mapper;
    private static final Logger logging = LoggerFactory.getLogger(CategoryService.class);

    public CategoryDTO createCateService(CreateCategoryRequest req) {
        try {
            logging.debug("creating categories server class");
            Category cate = new Category(req.getCategory(), LocalDateTime.now());
            repository.save(cate);
            return mapper.apply(cate);
        } catch (Exception e) {
            logging.error("cannot create categories service class");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public List<CategoryDTO> getAllCategory() {
        try {
            logging.debug("getting all categories");
            return repository.findAll()
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logging.error("Internal error cannot get all categories service class");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }
}
