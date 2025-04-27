package com.om.movieapp.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class S3Service {
//
//    @Value("${aws.accessKey}")
//    private String accessKey;
//
//    @Value("${aws.secretKey}")
//    private String secretKey;
//
//    @Value("${aws.region}")
//    private String region;
//
//    @Value("${aws.s3.bucketName}")
//    private String bucketName;
//
//    private S3Client s3Client;
//
//    @PostConstruct
//    private void initializeS3() {
//        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
//        this.s3Client = S3Client.builder()
//                .region(Region.of(region))
//                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
//                .build();
//    }
//
//    public void uploadFile(File file, String key) throws IOException {
//        if (file == null || !file.exists()) {
//            throw new IOException("File not found: " + (file != null ? file.getPath() : "null"));
//        }
//
//        try {
//            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                    .bucket(bucketName)
//                    .key(key)
//                    .build();
//
//            s3Client.putObject(putObjectRequest, RequestBody.fromFile(file.toPath()));
//            System.out.println("Uploaded to S3 at key: " + key);
//        } catch (Exception e) {
//            throw new IOException("S3 upload failed: " + e.getMessage(), e);
//        }
//    }
}