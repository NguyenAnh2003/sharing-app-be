package com.example.socialapi.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.socialapi.cloudinary.dto.UploadDTO;
import com.example.socialapi.config.CloudinaryConfig;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logging = LoggerFactory.getLogger(CloudinaryService.class);

    /* multipart request */
    public UploadDTO uploadMediaService(MultipartFile file) {
        try {
            logging.info("upload file cloudinary service");
            cloudinaryConfig = cloudinary.cloudinaryConfig();
            File uploadedFile = convertToFile(file);
            Map result = cloudinaryConfig.uploader().upload(uploadedFile,
                    Map.of("timestamp", LocalDateTime.now().toString()));
            boolean deletedFile = uploadedFile.delete();
            if(deletedFile) logging.debug("deleted file");
            else logging.debug("file doesn't exist");
            String rs = result.get("url").toString();
            return new UploadDTO(rs);
        } catch (Exception e) {
            logging.error("Internal error cannot upload file in cloud service");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    private File convertToFile(MultipartFile file) {
        try {
            logging.debug("converting multipart form file to file");
            File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.flush();
            fos.close();
            return convertedFile;
        } catch (Exception e) {
            logging.error("Internal error cannot convert from multipart form to file");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }
}
