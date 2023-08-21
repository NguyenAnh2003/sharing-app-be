package com.example.socialapi.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document
@NoArgsConstructor
public class Category {
    @Id
    private String id;
    @Indexed(unique = true)
    private String type;
    private  LocalDateTime timestamp;

    public Category(String id, String type, LocalDateTime timestamp) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Category(String type, LocalDateTime timestamp) {
        this.type = type;
        this.timestamp = timestamp;
    }
}
