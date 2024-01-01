package com.example.socialapi.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.socialapi.cloudinary.dto.UploadDTO;
import com.example.socialapi.config.CloudinaryConfig;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final CloudinaryConfig cloudinary;
    private Cloudinary cloudinaryConfig;
    /* multipart request */
    public UploadDTO uploadMediaService(MultipartFile file) {
        try {
            cloudinaryConfig = cloudinary.cloudinaryConfig();
            File uploadedFile = convertToFile(file);
            Map result = cloudinaryConfig.uploader().upload(uploadedFile,
                    Map.of("timestamp", LocalDateTime.now().toString()));
            boolean deletedFile = uploadedFile.delete();
            System.out.println(deletedFile ? "Delelted file" : "File doesnt exist");
            String rs = result.get("url").toString();
            return new UploadDTO(rs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File convertToFile(MultipartFile file) {
        try {
            File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.flush();
            fos.close();
            return convertedFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
