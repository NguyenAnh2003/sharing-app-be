package com.example.socialapi.cloudinary.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadRequest {
    private MultipartFile file;
}
