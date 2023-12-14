package com.example.socialapi.category;

import com.example.socialapi.category.dto.CateDTO;
import com.example.socialapi.category.request.CreateCateRequest;
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
public class CateController {
    private final CateService cateService;

    @PostMapping(value = "/seed")
    public ResponseEntity<CateDTO> createCategory(@RequestBody CreateCateRequest req) {
        return ResponseEntity.ok(cateService.createCateService(req));
    }

    // getALl
    @GetMapping
    public ResponseEntity<List<CateDTO>> fetchCategories() {
        return ResponseEntity.ok(cateService.getAllCategory());
    }

}
