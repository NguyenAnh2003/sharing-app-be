package com.example.socialapi.cloudinary;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Cloudinary - File upload")
@SecurityRequirement(name = "bearerAuth")
public class CloudinaryController {
}
