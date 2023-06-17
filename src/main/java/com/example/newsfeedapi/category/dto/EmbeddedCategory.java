package com.example.newsfeedapi.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class EmbeddedCategory {
    @Id
    private String id;
    private String category;

}
