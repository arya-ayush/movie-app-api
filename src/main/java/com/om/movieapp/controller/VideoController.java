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
import java.util.HashMap;
import java.util.Map;

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
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData,  @FormDataParam("name") String name,
            @FormDataParam("description") String description) {

        if (fileInputStream == null || fileMetaData == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No file provided").build();
        }

        Map<String, String> map = new HashMap<>();
        String fileName = fileMetaData.getFileName();

        map.put("fileName", fileName);
        map.put("fileContentType", fileMetaData.getType());

        // Save file locally
        File uploadDir = new File("uploads/");
        if (!uploadDir.exists()) uploadDir.mkdirs();

        File targetFile = new File(uploadDir, fileName);
        try (OutputStream out = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Local save failed: " + e.getMessage()).build();
        }

        // Upload to S3
        try (InputStream s3Stream = new FileInputStream(targetFile)) {
            PutObjectRequest putReq = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key("shorts/" + fileName)
                    .contentType(fileMetaData.getType())
                    .build();

            s3.putObject(putReq, RequestBody.fromInputStream(s3Stream, targetFile.length()));
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("S3 upload failed: " + e.getMessage()).build();
        }

        boolean deleted = targetFile.delete();
        if (!deleted) {
            map.put("warning", "Uploaded to S3, but failed to delete local file.");
        }

        Videos shortVideo = new Videos();
        shortVideo.setName(name);
        shortVideo.setDescription(description);
        shortVideo.setVideo( "https://" + BUCKET_NAME + ".s3.amazonaws.com/shorts/" + fileName);


        videoSave.saveVideo(shortVideo);
        map.put("message", "File uploaded successfully to S3");
        map.put("s3Url", "https://" + BUCKET_NAME + ".s3.amazonaws.com/shorts/" + fileName);

        return Response.ok(map).build();
    }
}