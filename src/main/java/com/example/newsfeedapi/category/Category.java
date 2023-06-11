package com.example.newsfeedapi.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@RequiredArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String id;
    @Indexed(unique = true)
    private final String type;
    private final LocalDateTime timestamp;
}
