package com.om.movieapp.controller;

import com.om.movieapp.model.UserDTO;
import com.om.movieapp.repository.UserLogDao;
import com.om.movieapp.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Autowired
    private UserLogDao userLogDao;
    @POST
    @Path("/save")
    public ResponseEntity<?> saveUser(@HeaderParam("apiKey") String apiKey, @Context Request request, UserDTO userDto) {

        User user = new User();

        try {

            if (userLogDao.userExists(userDto.getUser_id(), userDto.getEmail())) {
                // User already exists — return 200 OK with the existing user
                User existingUser = userLogDao.getUserByIdOrEmail(userDto.getUser_id(), userDto.getEmail());
                return ResponseEntity.ok(existingUser);  // HTTP 200 OK
            } else {
                // Create new user — return 201 Created
                user.setUser_id(userDto.getUser_id());
                user.setName(userDto.getName());
                user.setEmail(userDto.getEmail());
                user.setPhoto_url(userDto.getPhoto_url());
                user.setCoins(100);
                userLogDao.saveUser(user);

                return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully");  // HTTP 201 Created
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving user: " + e.getMessage());
        }
    }



}
