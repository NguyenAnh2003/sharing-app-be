package com.example.socialapi.cloudinary;

import com.example.socialapi.cloudinary.request.UploadRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "Cloudinary - File upload")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RequestMapping("/api/media")
public class CloudinaryController {
    private final CloudinaryService cloudinaryService;
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "File to upload",
                responses = {
                @ApiResponse(description = "Success", responseCode = "200"),
                @ApiResponse(description = "Cannot upload file", responseCode = "500")
                })
    public ResponseEntity<?> uploadMediaController(
            @RequestParam("file")MultipartFile request) {
        String imageURL = cloudinaryService.uploadMediaService(request);
        if(imageURL != null) return new ResponseEntity(imageURL, HttpStatus.OK);
        else return new ResponseEntity("Cannot upload file to s3",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
