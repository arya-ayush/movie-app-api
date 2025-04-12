package com.om.movieapp.controller;

import com.om.movieapp.model.UserDTO;
import com.om.movieapp.repository.UserLogDao;
import com.om.movieapp.service.User;
import org.springframework.beans.factory.annotation.Autowired;
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
            if (userLogDao.userExists(user.getUser_id(), user.getEmail())) {
                User existingUser = userLogDao.getUserByIdOrEmail(user.getUser_id(), user.getEmail());
                return ResponseEntity.status(200).body(existingUser);
            }else{

                user.setUser_id(userDto.getUser_id());   // Transferring the user ID
                user.setName(userDto.getName());
                user.setEmail(userDto.getEmail());
                user.setPhoto_url(userDto.getPhoto_url());

                userLogDao.saveUser(user);


                return ResponseEntity.ok("User saved successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving user: " + e.getMessage());
        }
    }



}
