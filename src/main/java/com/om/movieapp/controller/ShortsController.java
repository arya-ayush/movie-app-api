package com.om.movieapp.controller;

import com.om.movieapp.model.UserDTO;
import com.om.movieapp.repository.UserLogDao;
import com.om.movieapp.service.User;
import com.om.movieapp.service.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import java.util.List;

@Path("/shorts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShortsController {

    @Autowired
    private UserLogDao userLogDao;
    @GET
    @Path("/get")
    public ResponseEntity<?> saveUser(@HeaderParam("apiKey") String apiKey, @Context Request request, UserDTO userDto) {

        Videos videos = new Videos();

        try {


                // User already exists — return 200 OK with the existing user
                List<Videos> video = userLogDao.getRandomVideo();
                return ResponseEntity.ok(video);  // HTTP 200 OK


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error getting user: " + e.getMessage());
        }
    }



}
