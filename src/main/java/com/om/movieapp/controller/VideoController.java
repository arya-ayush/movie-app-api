package com.om.movieapp.controller;

import com.om.movieapp.repository.UserLogDao;
import com.om.movieapp.service.Videos;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.*;

@Path("/shorts")
public class VideoController {

    // 🔒 Replace these with your actual keys (for testing ONLY)
   DefaultCredentialsProvider credentialsProvider = DefaultCredentialsProvider.create();

    private static final String BUCKET_NAME = "fullmoviesapp";
    @Autowired
    private UserLogDao videoSave;
 private final S3Client s3 = S3Client.builder()
    .region(Region.AP_SOUTH_1)  
    .credentialsProvider(DefaultCredentialsProvider.create())
    .build();



        @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
        public Response handleFileUploadUsingCurl(
            @FormDataParam("url") String url,
            @FormDataParam("name") String name,
            @FormDataParam("description") String description) {
        if (url == null || url.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("URL is required").build();
        }

        File uploadDir = new File("uploads/");
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String fileName = null;
        File downloadedFile = null;


        try {
            // yt-dlp command

            String randomName = generateRandomString(10);  // e.g., "a7x92cfk9z"
            String outputTemplate = "uploads/" + randomName + ".%(ext)s";
            List<String> command = Arrays.asList(
                    "yt-dlp",
                    "-f", "mp4",
                    "-o", outputTemplate,
                    url
            );

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Read yt-dlp output
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("yt-dlp failed with exit code: " + exitCode).build();
            }

            // Find latest file downloaded
            File[] files = uploadDir.listFiles((dir, name1) -> name1.endsWith(".mp4"));
            if (files == null || files.length == 0) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("No MP4 file found after download").build();
            }

            // Get latest file
            downloadedFile = Arrays.stream(files)
                    .max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()))
                    .orElse(null);

            if (downloadedFile == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Could not determine downloaded file").build();
            }

            fileName = downloadedFile.getName();

            // Upload to S3
            try (InputStream s3Stream = new FileInputStream(downloadedFile)) {
                PutObjectRequest putReq = PutObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key("shorts/" + fileName)
                        .contentType("video/mp4")
                        .build();

                s3.putObject(putReq, RequestBody.fromInputStream(s3Stream, downloadedFile.length()));
            }

            // Save to DB
            Videos shortVideo = new Videos();
            shortVideo.setName(name);
            shortVideo.setDescription(description);
            shortVideo.setVideo("https://" + BUCKET_NAME + ".s3.amazonaws.com/shorts/" + fileName);
            videoSave.saveVideo(shortVideo);

            // Clean up
            downloadedFile.delete();

            Map<String, String> response = new HashMap<>();
            response.put("message", "YouTube video downloaded and uploaded to S3 successfully.");
            response.put("s3Url", "https://" + BUCKET_NAME + ".s3.amazonaws.com/shorts/" + fileName);

            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }
    private String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}