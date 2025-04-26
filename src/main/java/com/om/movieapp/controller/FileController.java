package com.om.movieapp.controller;


import com.om.movieapp.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;

@RestController
@Path("/upload")
@Consumes(MediaType.MULTIPART_FORM_DATA)

public class FileController {

    private final S3Service s3Service;

    @Autowired
    public FileController(S3Service s3Service) {
        this.s3Service = s3Service;
    }


    @POST
    @PostMapping("/profile")

    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = File.createTempFile("upload-", StringUtils.cleanPath(file.getOriginalFilename()));
            file.transferTo(tempFile);

            // Upload to S3
            s3Service.uploadFile(tempFile, "uploads/" + tempFile.getName());

            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed: " + e.getMessage());
        }
    }
}