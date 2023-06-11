package com.example.newsfeedapi.category;

import com.example.newsfeedapi.category.dto.CateDTO;
import com.example.newsfeedapi.category.request.CreateCateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CateController {
    private final CateService cateService;

    @PostMapping(value = "/seed")
    public ResponseEntity<CateDTO> createCategory(@RequestBody CreateCateRequest req) {
        return ResponseEntity.ok(cateService.createCateService(req));
    }

    // getALl

    // getCateById

}
