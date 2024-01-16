package com.example.socialapi.category;

import com.example.socialapi.category.dto.CategoryDTO;
import com.example.socialapi.category.request.CreateCategoryRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    private static final Logger logging = LoggerFactory.getLogger(CategoryController.class);

    @PostMapping(value = "/seed")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CreateCategoryRequest req) {
        try {
            logging.debug("debugging creating categories");
            return ResponseEntity.ok(cateService.createCateService(req));
        } catch (Exception e) {
            logging.error("Internal error cannot create categories");
            return new ResponseEntity("cannot create categories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // getALl
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> fetchCategories() {
        try {
            logging.debug("getting all categories");
            return ResponseEntity.ok(cateService.getAllCategory());
        } catch (Exception e) {
            logging.error("Internal error cannot get all categories");
            return new ResponseEntity("cannot get all categories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* get single category */
    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<CategoryDTO> fetchCategory(@PathVariable String categoryId) {
        try {
            logging.info("get category" + categoryId);
            return ResponseEntity.ok(cateService.getCategory(categoryId));
        } catch (Exception e) {
            logging.error("Cannot get category");
            return new ResponseEntity("Cannot get category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
