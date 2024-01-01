package com.example.socialapi.category;

import com.example.socialapi.category.dto.CategoryDTO;
import com.example.socialapi.category.request.CreateCategoryRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
@Tag(name = "Category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private final CategoryService cateService;

    @PostMapping(value = "/seed")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CreateCategoryRequest req) {
        return ResponseEntity.ok(cateService.createCateService(req));
    }

    // getALl
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> fetchCategories() {
        return ResponseEntity.ok(cateService.getAllCategory());
    }

}
