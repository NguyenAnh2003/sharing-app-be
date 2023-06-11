package com.example.newsfeedapi.category.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCateRequest {
    private String category;
}
